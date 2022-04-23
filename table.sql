create schema if not exists lte;
set search_path to lte;

create table if not exists tbcell
(
    city        varchar(255) null,
    sector_id   varchar(50)  not null primary key,
    sector_name varchar(255) not null,
    enodebid    int          not null,
    enodeb_name varchar(255) not null,
    earfcn      int          not null,
    pci         int          null,
    pss         int          null,
    sss         int          null,
    tac         int          null,
    vendor      varchar(255) null,
    longitude   float        not null,
    latitude    float        not null,
    style       varchar(255) null,
    azimuth     float        not null,
    height      float        null,
    electtilt   float        null,
    mechtilt    float        null,
    totletilt   float        not null
);

comment on table tbcell is '小区配置信息';
comment on column tbcell.city is '城市/地区名称';
comment on column tbcell.sector_id is '小区ID';
comment on column tbcell.sector_name is '小区名称';
comment on column tbcell.enodebid is '小区所属基站标识';
comment on column tbcell.enodeb_name is '基站名称';
comment on column tbcell.earfcn is '小区配置的频点编号';
comment on column tbcell.pci is '物理小区标识';
comment on column tbcell.pss is '主同步信号标识';
comment on column tbcell.sss is '辅同步信号表示';
comment on column tbcell.tac is '跟踪区编码';
comment on column tbcell.vendor is '供应商';
comment on column tbcell.longitude is '经度';
comment on column tbcell.latitude is '纬度';
comment on column tbcell.style is '基站类型';
comment on column tbcell.azimuth is '小区天线方位角';
comment on column tbcell.height is '小区天线高度';
comment on column tbcell.electtilt is '小区天线电下倾角';
comment on column tbcell.mechtilt is '小区天线机械下倾角';
comment on column tbcell.totletilt is '总下倾角';

drop trigger if exists tbcell_pci_insert on tbcell;
drop trigger if exists tbcell_pci_update on tbcell;
drop trigger if exists tbcell_totletilt_insert on tbcell;
drop trigger if exists tbcell_totletilt_update on tbcell;
drop function if exists tbcell_pci_fun;
drop function if exists tbcell_totletilt_fun;

create function tbcell_pci_fun() returns trigger
    language plpgsql
as
$$
begin
    new.pss = mod(new.pci, 3);
    new.sss = div(new.pci, 3);
    return new;
end;
$$;
create trigger tbcell_pci_insert
    before insert
    on tbcell
    for each row
execute procedure tbcell_pci_fun();

create trigger tbcell_pci_update
    before update
    on tbcell
    for each row
    when (new.pci <> old.pci)
execute procedure tbcell_pci_fun();

create function tbcell_totletilt_fun() returns trigger
    language plpgsql
as
$$
begin
    new.totletilt = new.electtilt + new.mechtilt;
    return new;
end;
$$;

create trigger tbcell_totletilt_insert
    before insert
    on tbcell
    for each row
execute procedure tbcell_totletilt_fun();

create trigger tbcell_totletilt_update
    before update
    on tbcell
    for each row
    when (new.electtilt <> old.electtilt or new.mechtilt <> old.electtilt)
execute procedure tbcell_totletilt_fun();

create table if not exists tbkpi
(
    id                           varchar(32)  not null primary key,
    start_time                   timestamp    not null,
    enodeb_name                  varchar(255) not null,
    sector_description           varchar(255) not null,
    sector_name                  varchar(255) not null,
    rrc_success                  int          not null,
    rrc_tries                    int          not null,
    rrc_success_rate             float        null,
    erab_success                 int          not null,
    erab_tries                   int          not null,
    erab_success_rate            float        null,
    enodeb_erab_release          int          not null,
    sector_erab_release          int          not null,
    erab_drop_rate               float        null,
    wireless_connection_rate     float        null,
    enodeb_ue_release            int          not null,
    ue_release                   int          not null,
    ue_success                   int          not null,
    wireless_drop_rate           float        null,
    intra_inter_success          int          not null,
    intra_inter_tries            int          not null,
    intra_intra_success          int          not null,
    intra_intra_tries            int          not null,
    inter_inter_success          int          not null,
    inter_inter_tries            int          not null,
    inter_intra_success          int          not null,
    inter_intra_tries            int          not null,
    intra_enodeb_success_rate    float        null,
    inter_enodeb_success_rate    float        null,
    intra_frequency_success_rate float        null,
    inter_frequency_success_rate float        null,
    handover_success_rate        float        null,
    upstream_capacity            bigint       not null,
    downstream_capacity          bigint       not null,
    rrc_retries                  int          not null,
    rrc_retries_rate             float        null,
    back_inter_intra_success     int          not null,
    back_inter_inter_success     int          not null,
    back_intra_intra_success     int          not null,
    back_intra_inter_success     int          not null,
    intra_enodeb_success         int          not null,
    inter_enodeb_success         int          not null
);

comment on table tbkpi is 'KPI指标统计数据';
comment on column tbkpi.id is 'ID';
comment on column tbkpi.start_time is '起始时间';
comment on column tbkpi.enodeb_name is '网元/基站名称';
comment on column tbkpi.sector_description is '小区';
comment on column tbkpi.sector_name is '小区名称';
comment on column tbkpi.rrc_success is 'RRC连接建立完成次数 (无)';
comment on column tbkpi.rrc_tries is 'RRC连接请求次数（包括重发） (无)';
comment on column tbkpi.rrc_success_rate is 'RRC建立成功率qf (%)';
comment on column tbkpi.erab_success is 'E-RAB建立成功总次数 (无)';
comment on column tbkpi.erab_tries is 'E-RAB建立尝试总次数 (无)';
comment on column tbkpi.erab_success_rate is 'E-RAB建立成功率2 (%)';
comment on column tbkpi.enodeb_erab_release is 'eNodeB触发的E-RAB异常释放总次数 (无)';
comment on column tbkpi.sector_erab_release is '小区切换出E-RAB异常释放总次数 (无)';
comment on column tbkpi.erab_drop_rate is 'E-RAB掉线率(新) (%)';
comment on column tbkpi.wireless_connection_rate is '无线接通率ay (%)';
comment on column tbkpi.enodeb_ue_release is 'eNodeB发起的S1 RESET导致的UE Context释放次数 (无)';
comment on column tbkpi.ue_release is 'UE Context异常释放次数 (无)';
comment on column tbkpi.ue_success is 'UE Context建立成功总次数 (无)';
comment on column tbkpi.wireless_drop_rate is '无线掉线率 (%)';
comment on column tbkpi.intra_inter_success is 'eNodeB内异频切换出成功次数 (无)';
comment on column tbkpi.intra_inter_tries is 'eNodeB内异频切换出尝试次数 (无)';
comment on column tbkpi.intra_intra_success is 'eNodeB内同频切换出成功次数 (无)';
comment on column tbkpi.intra_intra_tries is 'eNodeB内同频切换出尝试次数 (无)';
comment on column tbkpi.inter_inter_success is 'eNodeB间异频切换出成功次数 (无)';
comment on column tbkpi.inter_inter_tries is 'eNodeB间异频切换出尝试次数 (无)';
comment on column tbkpi.inter_intra_success is 'eNodeB间同频切换出成功次数 (无)';
comment on column tbkpi.inter_intra_tries is 'eNodeB间同频切换出尝试次数 (无)';
comment on column tbkpi.intra_enodeb_success_rate is 'eNB内切换成功率 (%)';
comment on column tbkpi.inter_enodeb_success_rate is 'eNB间切换成功率 (%)';
comment on column tbkpi.intra_frequency_success_rate is '同频切换成功率zsp (%)';
comment on column tbkpi.inter_frequency_success_rate is '异频切换成功率zsp (%)';
comment on column tbkpi.handover_success_rate is '切换成功率 (%)';
comment on column tbkpi.upstream_capacity is '小区PDCP层所接收到的上行数据的总吞吐量 (比特)';
comment on column tbkpi.downstream_capacity is '小区PDCP层所发送的下行数据的总吞吐量 (比特)';
comment on column tbkpi.rrc_retries is 'RRC重建请求次数 (无)';
comment on column tbkpi.rrc_retries_rate is 'RRC连接重建比率 (%)';
comment on column tbkpi.back_inter_intra_success is '通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)';
comment on column tbkpi.back_inter_inter_success is '通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)';
comment on column tbkpi.back_intra_intra_success is '通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)';
comment on column tbkpi.back_intra_inter_success is '通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)';
comment on column tbkpi.intra_enodeb_success is 'eNB内切换出成功次数 (次)';
comment on column tbkpi.inter_enodeb_success is 'eNB内切换出请求次数 (次)';

create table if not exists tbprb
(
    id                 varchar(32)  not null primary key,
    start_time         timestamp    not null,
    enodeb_name        varchar(255) not null,
    sector_description varchar(255) not null,
    sector_name        varchar(255) not null,
    noise0             int          not null,
    noise1             int          not null,
    noise2             int          not null,
    noise3             int          not null,
    noise4             int          not null,
    noise5             int          not null,
    noise6             int          not null,
    noise7             int          not null,
    noise8             int          not null,
    noise9             int          not null,
    noise10            int          not null,
    noise11            int          not null,
    noise12            int          not null,
    noise13            int          not null,
    noise14            int          not null,
    noise15            int          not null,
    noise16            int          not null,
    noise17            int          not null,
    noise18            int          not null,
    noise19            int          not null,
    noise20            int          not null,
    noise21            int          not null,
    noise22            int          not null,
    noise23            int          not null,
    noise24            int          not null,
    noise25            int          not null,
    noise26            int          not null,
    noise27            int          not null,
    noise28            int          not null,
    noise29            int          not null,
    noise30            int          not null,
    noise31            int          not null,
    noise32            int          not null,
    noise33            int          not null,
    noise34            int          not null,
    noise35            int          not null,
    noise36            int          not null,
    noise37            int          not null,
    noise38            int          not null,
    noise39            int          not null,
    noise40            int          not null,
    noise41            int          not null,
    noise42            int          not null,
    noise43            int          not null,
    noise44            int          not null,
    noise45            int          not null,
    noise46            int          not null,
    noise47            int          not null,
    noise48            int          not null,
    noise49            int          not null,
    noise50            int          not null,
    noise51            int          not null,
    noise52            int          not null,
    noise53            int          not null,
    noise54            int          not null,
    noise55            int          not null,
    noise56            int          not null,
    noise57            int          not null,
    noise58            int          not null,
    noise59            int          not null,
    noise60            int          not null,
    noise61            int          not null,
    noise62            int          not null,
    noise63            int          not null,
    noise64            int          not null,
    noise65            int          not null,
    noise66            int          not null,
    noise67            int          not null,
    noise68            int          not null,
    noise69            int          not null,
    noise70            int          not null,
    noise71            int          not null,
    noise72            int          not null,
    noise73            int          not null,
    noise74            int          not null,
    noise75            int          not null,
    noise76            int          not null,
    noise77            int          not null,
    noise78            int          not null,
    noise79            int          not null,
    noise80            int          not null,
    noise81            int          not null,
    noise82            int          not null,
    noise83            int          not null,
    noise84            int          not null,
    noise85            int          not null,
    noise86            int          not null,
    noise87            int          not null,
    noise88            int          not null,
    noise89            int          not null,
    noise90            int          not null,
    noise91            int          not null,
    noise92            int          not null,
    noise93            int          not null,
    noise94            int          not null,
    noise95            int          not null,
    noise96            int          not null,
    noise97            int          not null,
    noise98            int          not null,
    noise99            int          not null
);

comment on table tbprb is 'PRB干扰数据';
comment on column tbprb.id is 'ID';
comment on column tbprb.start_time is '起始时间';
comment on column tbprb.enodeb_name is '网元/基站名称';
comment on column tbprb.sector_description is '小区描述';
comment on column tbprb.sector_name is '小区名';
comment on column tbprb.noise0 is '第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise1 is '第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise2 is '第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise3 is '第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise4 is '第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise5 is '第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise6 is '第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise7 is '第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise8 is '第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise9 is '第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise10 is '第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise11 is '第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise12 is '第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise13 is '第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise14 is '第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise15 is '第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise16 is '第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise17 is '第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise18 is '第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise19 is '第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise20 is '第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise21 is '第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise22 is '第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise23 is '第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise24 is '第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise25 is '第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise26 is '第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise27 is '第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise28 is '第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise29 is '第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise30 is '第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise31 is '第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise32 is '第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise33 is '第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise34 is '第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise35 is '第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise36 is '第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise37 is '第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise38 is '第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise39 is '第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise40 is '第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise41 is '第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise42 is '第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise43 is '第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise44 is '第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise45 is '第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise46 is '第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise47 is '第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise48 is '第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise49 is '第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise50 is '第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise51 is '第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise52 is '第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise53 is '第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise54 is '第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise55 is '第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise56 is '第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise57 is '第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise58 is '第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise59 is '第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise60 is '第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise61 is '第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise62 is '第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise63 is '第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise64 is '第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise65 is '第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise66 is '第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise67 is '第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise68 is '第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise69 is '第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise70 is '第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise71 is '第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise72 is '第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise73 is '第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise74 is '第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise75 is '第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise76 is '第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise77 is '第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise78 is '第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise79 is '第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise80 is '第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise81 is '第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise82 is '第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise83 is '第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise84 is '第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise85 is '第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise86 is '第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise87 is '第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise88 is '第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise89 is '第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise90 is '第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise91 is '第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise92 is '第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise93 is '第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise94 is '第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise95 is '第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise96 is '第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise97 is '第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise98 is '第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';
comment on column tbprb.noise99 is '第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)';

create table ucenter_member
(
    id           bigserial primary key,
    mobile       varchar(11)  default '',
    passwd       varchar(255) default null,
    nickname     varchar(50)  default null,
    avatar       varchar(255) default null,
    is_disabled  boolean      default false not null,
    is_deleted   boolean      default false not null,
    gmt_create   timestamp                  not null,
    gmt_modified timestamp                  not null
);

comment on table ucenter_member is '用户表';
comment on column ucenter_member.id is '用户id';
comment on column ucenter_member.mobile is '手机号';
comment on column ucenter_member.passwd is '密码';
comment on column ucenter_member.nickname is '昵称';
comment on column ucenter_member.avatar is '用户头像';
comment on column ucenter_member.is_disabled is '是否禁用 1（true）已禁用，  0（false）未禁用';
comment on column ucenter_member.is_deleted is '逻辑删除 1（true）已删除， 0（false）未删除';
comment on column ucenter_member.gmt_create is '创建时间';
comment on column ucenter_member.gmt_modified is '更新时间';
