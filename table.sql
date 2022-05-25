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

create table if not exists acl_permission
(
    id               bigserial primary key,
    pid              bigint      not null default 0,
    name             varchar(20) not null default '',
    type             smallint    not null default 0,
    permission_value varchar(50)          default null,
    path             varchar(100)         default null,
    component        varchar(100)         default null,
    icon             varchar(50)          default null,
    is_disabled      smallint    not null default 0,
    is_deleted       smallint             default 0,
    gmt_create       timestamp            default null,
    gmt_modified     timestamp            default null
);

create index idx_pid on acl_permission (pid);
create unique index idx_permission_name on acl_permission (pid, name, is_deleted);

comment on table acl_permission is '权限表';
comment on column acl_permission.id is '权限id';
comment on column acl_permission.pid is '所属上级';
comment on column acl_permission.name is '名称';
comment on column acl_permission.type is '类型(1: 菜单, 2: 按钮)';
comment on column acl_permission.permission_value is '权限值';
comment on column acl_permission.path is '访问路径';
comment on column acl_permission.component is '组件路径';
comment on column acl_permission.icon is '图标';
comment on column acl_permission.is_disabled is '状态(1: 禁止访问, 0: 正常访问)';
comment on column acl_permission.is_deleted is '逻辑删除(null: 已删除, 0: 未删除)';
comment on column acl_permission.gmt_create is '创建时间';
comment on column acl_permission.gmt_modified is '更新时间';


insert into acl_permission values (1,0,'全部数据',0,'','','','',0,0,'2022-05-06 21:28:25.000000','2022-05-06 21:28:28.000000');
insert into acl_permission values (2,1,'权限控制',1,'','/acl','Layout','',0,0,'2022-05-06 21:29:25.000000','2022-05-06 21:29:30.000000');
insert into acl_permission values (3,2,'用户列表',1,'','user/list','/acl/user/list','',0,0,'2022-05-06 21:31:30.000000','2022-05-06 21:31:31.000000');
insert into acl_permission values (4,2,'角色管理',1,'','role/list','/acl/role/list','',0,0,'2022-05-06 21:32:40.000000','2022-05-06 21:32:41.000000');
insert into acl_permission values (5,2,'权限管理',1,'','permission/list','/acl/menu/list','',0,0,'2022-05-06 23:21:44.000000','2022-05-06 23:21:47.000000');
insert into acl_permission values (6,3,'查看用户',2,'user.list','','','',0,0,'2022-05-06 21:38:38.000000','2022-05-06 22:38:39.000000');
insert into acl_permission values (7,3,'添加用户',2,'user.add','user/add','/acl/user/form','',0,0,'2022-05-06 21:39:24.000000','2022-05-06 21:39:25.000000');
insert into acl_permission values (8,3,'修改用户',2,'user.update','user/update/:id','/acl/user/form','',0,0,'2022-05-06 21:40:55.000000','2022-05-06 21:40:56.000000');
insert into acl_permission values (9,3,'删除用户',2,'user.remove','','','',0,0,'2022-05-06 21:42:05.000000','2022-05-06 22:42:06.000000');
insert into acl_permission values (10,3,'分配角色',2,'user.assign','user/role/:id','/acl/user/roleForm','',0,0,'2022-05-21 19:42:39.000000','2022-05-21 19:42:40.000000');
insert into acl_permission values (11,4,'查看角色',2,'role.list','','','',0,0,'2022-05-06 21:43:25.000000','2022-05-06 21:43:30.000000');
insert into acl_permission values (12,4,'添加角色',2,'role.add','role/add','/acl/role/form','',0,0,'2022-05-06 21:44:35.000000','2022-05-06 22:44:36.000000');
insert into acl_permission values (13,4,'修改角色',2,'role.update','role/update/:id','/acl/role/form','',0,0,'2022-05-06 21:45:30.000000','2022-05-06 22:45:31.000000');
insert into acl_permission values (14,4,'删除角色',2,'role.remove','','','',0,0,'2022-05-06 21:45:58.000000','2022-05-06 21:45:58.000000');
insert into acl_permission values (15,4,'角色权限',2,'role.acl','role/distribution/:id','/acl/role/roleForm','',0,0,'2022-05-06 21:47:50.000000','2022-05-06 21:47:51.000000');
insert into acl_permission values (16,5,'查看权限',2,'permission.list','','','',0,0,'2022-05-06 23:40:30.000000','2022-05-06 23:40:32.000000');
insert into acl_permission values (17,5,'添加权限',2,'permission.add','','','',0,0,'2022-05-06 23:31:14.000000','2022-05-06 23:31:15.000000');
insert into acl_permission values (18,5,'修改权限',2,'permission.update','','','',0,0,'2022-05-06 23:31:17.000000','2022-05-06 23:31:19.000000');
insert into acl_permission values (19,5,'删除权限',2,'permission.remove','','','',0,0,'2022-05-06 23:31:21.000000','2022-05-06 23:31:22.000000');
insert into acl_permission values (20,1,'业务查询',1,'','/query','Layout','',0,0,'2022-05-16 23:32:49.000000','2022-05-16 23:32:56.000000');
insert into acl_permission values (21,20,'小区配置信息',1,'','cell','/query/cell','',0,0,'2022-05-16 23:35:21.000000','2022-05-16 23:35:24.000000');
insert into acl_permission values (22,20,'KPI指标信息查询',1,'','kpi','/query/kpi','',0,0,'2022-05-16 23:36:59.000000','2022-05-16 23:37:01.000000');
insert into acl_permission values (23,20,'PRB干扰数据查询',1,'','prb','/query/prb','',0,0,'2022-05-16 23:37:02.000000','2022-05-16 23:37:03.000000');
insert into acl_permission values (24,21,'查询小区数据',2,'cell.list','','','',0,0,'2022-05-21 21:29:03.172733','2022-05-21 21:29:03.172733');
insert into acl_permission values (25,22,'查询KPI数据',2,'kpi.list','','','',0,0,'2022-05-21 21:28:45.891930','2022-05-21 21:28:45.891930');
insert into acl_permission values (26,23,'查询PRB数据',2,'prb.list','','','',0,0,'2022-05-21 21:28:24.827355','2022-05-21 21:28:24.827355');
insert into acl_permission values (27,1,'管理面板',1,'','/manage','Layout','',0,0,'2022-05-19 20:33:20.000000','2022-05-19 20:33:22.000000');
insert into acl_permission values (28,27,'数据管理',1,'','data','/manage/data','',0,0,'2022-05-19 20:35:58.000000','2022-05-21 21:33:05.917733');
insert into acl_permission values (29,28,'数据导入',2,'data.import','','','',0,0,'2022-05-21 21:34:25.233147','2022-05-21 21:34:25.233147');
insert into acl_permission values (30,28,'数据导出',2,'data.export','','','',0,0,'2022-05-21 21:34:35.481737','2022-05-21 21:34:35.481737');

create table if not exists acl_role
(
    id           bigserial primary key,
    name         varchar(20) not null default '',
    remark       varchar(255)         default null,
    is_deleted   smallint             default 0,
    gmt_create   timestamp   not null,
    gmt_modified timestamp   not null
);

create unique index idx_role_name on acl_role (name, is_deleted);

comment on table acl_role is '角色表';
comment on column acl_role.id is '角色id';
comment on column acl_role.name is '角色名称';
comment on column acl_role.remark is '备注';
comment on column acl_role.is_deleted is '逻辑删除(null: 已删除, 0: 未删除)';
comment on column acl_role.gmt_create is '创建时间';
comment on column acl_role.gmt_modified is '更新时间';

insert into acl_role values (1,'administrator','管理员',0,'2022-05-21 22:56:59.000000','2022-05-21 22:56:59.000000');

create table if not exists acl_user
(
    id           bigserial primary key,
    password     varchar(32) not null default '',
    name         varchar(50) not null default '',
    avatar       varchar(255)         default null,
    is_disabled  smallint    not null default 0,
    is_deleted   smallint             default 0,
    gmt_create   timestamp   not null,
    gmt_modified timestamp   not null
);

create unique index idx_user_name on acl_user (name, is_deleted);

comment on table acl_user is '用户表';
comment on column acl_user.id is '用户id';
comment on column acl_user.password is '密码';
comment on column acl_user.name is '用户名';
comment on column acl_user.avatar is '用户头像';
comment on column acl_user.is_disabled is '状态(1: 已禁用, 0: 正常使用)';
comment on column acl_user.is_deleted is '逻辑删除(null: 已删除, 0: 未删除)';
comment on column acl_user.gmt_create is '创建时间';
comment on column acl_user.gmt_modified is '更新时间';

insert into acl_user values (1,'96E79218965EB72C92A549DD5A330112','admin','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',0,0,'2022-05-06 20:11:08.000000','2022-05-06 20:11:14.000000');

create table if not exists acl_role_permission
(
    id            bigserial primary key,
    role_id       bigint    not null default 0,
    permission_id bigint    not null default 0,
    is_deleted    smallint           default 0,
    gmt_create    timestamp not null,
    gmt_modified  timestamp not null
);

create index idx_rp_role_id on acl_role_permission (role_id);
create index idx_rp_permission_id on acl_role_permission (permission_id);
create unique index idx_rp on acl_role_permission (role_id, permission_id, is_deleted);

comment on table acl_role_permission is '角色权限关系表';
comment on column acl_role_permission.id is '主键id';
comment on column acl_role_permission.role_id is '角色id';
comment on column acl_role_permission.permission_id is '权限id';
comment on column acl_role_permission.is_deleted is '逻辑删除(null: 已删除, 0: 未删除)';
comment on column acl_role_permission.gmt_create is '创建时间';
comment on column acl_role_permission.gmt_modified is '更新时间';

insert into acl_role_permission values (1,1,4,0,'2022-05-21 21:38:52.828351','2022-05-21 22:48:34.601954');
insert into acl_role_permission values (2,1,10,0,'2022-05-21 21:38:52.829319','2022-05-21 22:48:34.662433');
insert into acl_role_permission values (3,1,11,0,'2022-05-21 21:38:52.829319','2022-05-21 22:48:34.721614');
insert into acl_role_permission values (4,1,12,0,'2022-05-21 21:38:52.829319','2022-05-21 22:48:34.782177');
insert into acl_role_permission values (5,1,13,0,'2022-05-21 21:38:52.829319','2022-05-21 22:48:34.842712');
insert into acl_role_permission values (6,1,14,0,'2022-05-21 21:38:52.830315','2022-05-21 22:48:34.902392');
insert into acl_role_permission values (7,1,5,0,'2022-05-21 21:38:52.830315','2022-05-21 22:48:34.962343');
insert into acl_role_permission values (8,1,15,0,'2022-05-21 21:38:52.830315','2022-05-21 22:48:35.021937');
insert into acl_role_permission values (9,1,16,0,'2022-05-21 21:38:52.831313','2022-05-21 22:48:35.081652');
insert into acl_role_permission values (10,1,17,0,'2022-05-21 21:38:52.831313','2022-05-21 22:48:35.141543');
insert into acl_role_permission values (11,1,18,0,'2022-05-21 21:38:52.831313','2022-05-21 22:48:35.202228');
insert into acl_role_permission values (12,1,1,0,'2022-05-21 21:38:52.819221','2022-05-21 22:48:33.443862');
insert into acl_role_permission values (13,1,19,0,'2022-05-21 21:38:52.832310','2022-05-21 22:48:33.512554');
insert into acl_role_permission values (14,1,20,0,'2022-05-21 21:38:52.832310','2022-05-21 22:48:33.574435');
insert into acl_role_permission values (15,1,28,0,'2022-05-21 21:38:52.832310','2022-05-21 22:48:33.634617');
insert into acl_role_permission values (16,1,21,0,'2022-05-21 21:38:52.832310','2022-05-21 22:48:33.695861');
insert into acl_role_permission values (17,1,27,0,'2022-05-21 21:38:52.833307','2022-05-21 22:48:33.757181');
insert into acl_role_permission values (18,1,22,0,'2022-05-21 21:38:52.833307','2022-05-21 22:48:33.816347');
insert into acl_role_permission values (19,1,26,0,'2022-05-21 21:38:52.833307','2022-05-21 22:48:33.876915');
insert into acl_role_permission values (20,1,23,0,'2022-05-21 21:38:52.833307','2022-05-21 22:48:33.937871');
insert into acl_role_permission values (21,1,24,0,'2022-05-21 21:38:52.834305','2022-05-21 22:48:33.997534');
insert into acl_role_permission values (22,1,29,0,'2022-05-21 21:38:52.834305','2022-05-21 22:48:34.057144');
insert into acl_role_permission values (23,1,30,0,'2022-05-21 21:38:52.834305','2022-05-21 22:48:34.117736');
insert into acl_role_permission values (24,1,2,0,'2022-05-21 21:38:52.824209','2022-05-21 22:48:34.178794');
insert into acl_role_permission values (25,1,3,0,'2022-05-21 21:38:52.824209','2022-05-21 22:48:34.240238');
insert into acl_role_permission values (26,1,6,0,'2022-05-21 21:38:52.825202','2022-05-21 22:48:34.301015');
insert into acl_role_permission values (27,1,7,0,'2022-05-21 21:38:52.825202','2022-05-21 22:48:34.362378');
insert into acl_role_permission values (28,1,8,0,'2022-05-21 21:38:52.827197','2022-05-21 22:48:34.422622');
insert into acl_role_permission values (29,1,9,0,'2022-05-21 21:38:52.827197','2022-05-21 22:48:34.483532');
insert into acl_role_permission values (30,1,25,0,'2022-05-21 21:38:52.828351','2022-05-21 22:48:34.542395');

create table acl_user_role
(
    id           bigserial primary key,
    role_id      bigint    not null default 0,
    user_id      bigint    not null default 0,
    is_deleted   smallint           default 0,
    gmt_create   timestamp not null,
    gmt_modified timestamp not null
);

create index idx_ur_role_id on acl_user_role (role_id);
create index idx_ur_user_id on acl_user_role (user_id);
create unique index idx_ur on acl_user_role (role_id, user_id, is_deleted);

comment on table acl_user_role is '用户角色关系表';
comment on column acl_user_role.id is '主键id';
comment on column acl_user_role.role_id is '角色id';
comment on column acl_user_role.user_id is '用户id';
comment on column acl_user_role.is_deleted is '逻辑删除(null: 已删除, 0: 未删除)';
comment on column acl_user_role.gmt_create is '创建时间';
comment on column acl_user_role.gmt_modified is '更新时间';

insert into acl_user_role values (1,1,1,0,'2022-05-06 23:45:55.000000','2022-05-06 23:45:55.000000');
