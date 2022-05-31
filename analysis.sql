drop function if exists create_tbc2i;
drop table if exists tbc2inew;
drop function if exists get_mid_tuple;
drop function if exists get_tuples;
drop function if exists get_tuples_2;

-- 分析生成的tbc2i表格
create table tbc2inew
(
    scell    varchar(50) not null,
    ncell    varchar(50) not null,
    c2i_mean float       null,
    std      float       null,
    prb_c2i9 float       null default null,
    prb_abs6 float       null default null,
    id       serial8     not null primary key
);

comment on column tbc2inew.scell is '主小区';
comment on column tbc2inew.ncell is '邻小区';
comment on column tbc2inew.c2i_mean is 'C2I均值';
comment on column tbc2inew.std is 'C2I方差';
comment on column tbc2inew.prb_c2i9 is 'C2I差值小于9的概率';
comment on column tbc2inew.prb_abs6 is 'C2I绝对值小于6的概率';
comment on column tbc2inew.id is 'id';
-- 从tbmrodata中生成tbc2i，能够根据传入的参数过滤掉不合理的数据
create or replace function create_tbc2i(x int) returns void as
$$
declare
begin
    delete from tbc2inew;
    insert into tbc2inew
    select serving_sector, interfering_sector, c2i_mean, std
    from (
             select serving_sector,
                    interfering_sector,
                    avg(lte_sc_rsrp - lte_nc_rsrp)    c2i_mean,
                    stddev(lte_sc_rsrp - lte_nc_rsrp) std
             from tbmrodata
             group by serving_sector, interfering_sector
             having count(*) > x
         ) tbc2i;
end
$$ language plpgsql;
-- 从三个字符串中获取中间大小的字符串
create or replace function get_mid_tuple(a varchar(50), b varchar(50), c varchar(50)) returns varchar(50)
as
$$
declare
    a_ varchar(50) := least(a, b, c);
    b_ varchar(50) := greatest(a, b, c);
begin
    return case
               when (a <> a_ and a <> b_) then a
               when (b <> a_ and b <> b_) then b
               else c
        end;
end
$$ language plpgsql;

-- 获得重叠覆盖干扰小区三元组
-- 首先，找到满足条件的候选区域对，且要满足每一对互为邻小区的
-- 然后，通过多表连接的方式把找到a->b,b->c 且 a != c的abc三个区域
-- 随后，检验区域ac或ca满足条件
-- 最后，通过对三元组排序来进行去重
create or replace function get_tuples_2(x float)
    returns table
            (
                a varchar(50),
                b varchar(50),
                c varchar(50)
            )
as
$$
declare
begin
    return query
        with candidate as (
            select scell, ncell
            from tbc2inew ti
            where ti.scell <> ti.ncell
              and prb_abs6 >= x
              and exists(
                    select *
                    from tbc2inew ti2
                    where ti.scell = ti2.ncell
                      and ti.ncell = ti2.scell
                )
        )
        select distinct least(p.scell, p.ncell, q.ncell)         a,
                        get_mid_tuple(p.scell, p.ncell, q.ncell) b,
                        greatest(p.scell, p.ncell, q.ncell)      c
        from candidate p
                 inner join candidate q on (p.ncell = q.scell and p.scell <> q.ncell)
        where exists(
                      select *
                      from candidate r
                      where ((r.scell = q.ncell and r.ncell = p.scell) or (r.scell = p.scell and r.ncell = q.ncell))
                  );
end;
$$ language plpgsql;
-- 获得重叠覆盖干扰小区三元组
-- 首先，找到满足条件的候选区域对
-- 然后，通过多表连接的方式把找到a->b,b->c 且 a != c的abc三个区域
-- 随后，检验区域ac或ca满足条件
-- 最后，通过对三元组排序来进行去重
create or replace function get_tuples(x float)
    returns table
            (
                a varchar(50),
                b varchar(50),
                c varchar(50)
            )
as
$$
declare
begin
    return query
        with candidate as (
            select scell, ncell
            from tbc2inew ti
            where ti.scell <> ti.ncell
              and prb_abs6 >= x
        )
        select distinct least(p.scell, p.ncell, q.ncell)         a,
                        get_mid_tuple(p.scell, p.ncell, q.ncell) b,
                        greatest(p.scell, p.ncell, q.ncell)      c
        from candidate p
                 inner join candidate q on (p.ncell = q.scell and p.scell <> q.ncell)
        where exists(
                      select *
                      from candidate r
                      where (r.scell = q.ncell and r.ncell = p.scell) or (r.scell = p.scell and r.ncell = q.ncell)
                  );
end;
$$ language plpgsql;