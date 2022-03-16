create table tbcell
(
    city        varchar(255) comment '城市/地区名称',
    sector_id   varchar(50)  not null comment '小区ID',
    sector_name varchar(255) not null comment '小区名称',
    enodebid    int          not null comment '小区所属基站标识',
    enodeb_name varchar(255) not null comment '基站名称',
    earfcn      int          not null comment '小区配置的频点编号',
    pci         int comment '物理小区标识',
    pss         int comment '主同步信号标识',
    sss         int comment '辅同步信号表示',
    tac         int comment '跟踪区编码',
    vendor      varchar(255) comment '供应商',
    longitude   float        not null comment '经度',
    latitude    float        not null comment '纬度',
    style       varchar(255) comment '基站类型',
    azimuth     float        not null comment '小区天线方位角',
    height      float comment '小区天线高度',
    electtilt   float comment '小区天线电下倾角',
    mechtilt    float comment '小区天线机械下倾角',
    totletilt   float        not null comment '总下倾角',
    primary key tbcell_pk (sector_id)
);

create trigger tbcell_pss
    before insert
    on tbcell
    for each row
begin
    set new.pss = mod(new.pci, 3);
end;

create trigger tbcell_sss
    before insert
    on tbcell
    for each row
begin
    set new.sss = new.pci / 3;
end;

create trigger tbcell_totletilt
    before insert
    on tbcell
    for each row
begin
    set new.totletilt = new.electtilt + new.mechtilt;
end;
