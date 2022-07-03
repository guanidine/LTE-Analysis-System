-- 非聚集索引设计

-- 案例一：针对where子句建立联合索引，从执行计划看效果显著
-- 注意但从查询时间看可能受到数据量和缓存命中的影响，效果不明显
drop index if exists tbprb_non_cluster_ind;
explain
(analyze,buffers)
select start_time, sum(noise0) as result
from tbprb
where enodeb_name = 'B马千秋政府-HLWE'
  and start_time between '2020-07-18 10:45:00.000' and '2020-07-20 10:45:00.000'
group by start_time;

create index tbprb_non_cluster_ind on tbprb (enodeb_name, start_time);

explain
(analyze,buffers)
select start_time, sum(noise0) as result
from tbprb
where enodeb_name = 'B马千秋煤矿-HLHF'
  and start_time between '2020-07-18 10:45:00.000' and '2020-07-19 10:45:00.000'
group by start_time;

-- 案例二：针对where子句建立覆盖索引，从执行计划看效果显著
-- 注意但从查询时间看可能受到查询数据量和缓存命中的影响，效果不明显（tbcell表数据量小，而且一次命中后数据几乎全部都在缓存，很难看出明显区别）
drop index if exists tbcell_non_cluster_ind;
explain
(analyze,buffers)
select distinct enodebid, enodeb_name
from tbcell
order by enodebid;
create index tbcell_non_cluster_ind on tbcell (enodebid, enodeb_name);
explain
(analyze,buffers)
select distinct enodebid, enodeb_name
from tbcell
order by enodebid;