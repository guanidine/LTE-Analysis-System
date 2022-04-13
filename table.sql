create database if not exists lte;
use lte;

create table if not exists tbcell
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

drop trigger if exists tbcell_pss;
create trigger tbcell_pss
    before insert
    on tbcell
    for each row
begin
    set new.pss = mod(new.pci, 3);
end;

drop trigger if exists tbcell_sss;
create trigger tbcell_sss
    before insert
    on tbcell
    for each row
begin
    set new.sss = new.pci / 3;
end;

drop trigger if exists tbcell_totletilt;
create trigger tbcell_totletilt
    before insert
    on tbcell
    for each row
begin
    set new.totletilt = new.electtilt + new.mechtilt;
end;

create table if not exists tbkpi
(
    id                           varchar(32)  not null comment 'ID',
    start_time                   varchar(255) not null comment '起始时间',
    enodeb_name                  varchar(255) not null comment '网元/基站名称',
    sector_description           varchar(255) not null comment '小区',
    sector_name                  varchar(255) not null comment '小区名称',
    rrc_success                  int          not null comment 'RRC连接建立完成次数 (无)',
    rrc_tries                    int          not null comment 'RRC连接请求次数（包括重发） (无)',
    rrc_success_rate             float        null comment 'RRC建立成功率qf (%)',
    erab_success                 int          not null comment 'E-RAB建立成功总次数 (无)',
    erab_tries                   int          not null comment 'E-RAB建立尝试总次数 (无)',
    erab_success_rate            float        null comment 'E-RAB建立成功率2 (%)',
    enodeb_erab_release          int          not null comment 'eNodeB触发的E-RAB异常释放总次数 (无)',
    sector_erab_release          int          not null comment '小区切换出E-RAB异常释放总次数 (无)',
    erab_drop_rate               float        null comment 'E-RAB掉线率(新) (%)',
    wireless_connection_rate     float        null comment '无线接通率ay (%)',
    enodeb_ue_release            int          not null comment 'eNodeB发起的S1 RESET导致的UE Context释放次数 (无)',
    ue_release                   int          not null comment 'UE Context异常释放次数 (无)',
    ue_success                   int          not null comment 'UE Context建立成功总次数 (无)',
    wireless_drop_rate           float        null comment '无线掉线率 (%)',
    intra_inter_success          int          not null comment 'eNodeB内异频切换出成功次数 (无)',
    intra_inter_tries            int          not null comment 'eNodeB内异频切换出尝试次数 (无)',
    intra_intra_success          int          not null comment 'eNodeB内同频切换出成功次数 (无)',
    intra_intra_tries            int          not null comment 'eNodeB内同频切换出尝试次数 (无)',
    inter_inter_success          int          not null comment 'eNodeB间异频切换出成功次数 (无)',
    inter_inter_tries            int          not null comment 'eNodeB间异频切换出尝试次数 (无)',
    inter_intra_success          int          not null comment 'eNodeB间同频切换出成功次数 (无)',
    inter_intra_tries            int          not null comment 'eNodeB间同频切换出尝试次数 (无)',
    intra_enodeb_success_rate    float        null comment 'eNB内切换成功率 (%)',
    inter_enodeb_success_rate    float        null comment 'eNB间切换成功率 (%)',
    intra_frequency_success_rate float        null comment '同频切换成功率zsp (%)',
    inter_frequency_success_rate float        null comment '异频切换成功率zsp (%)',
    handover_success_rate        float        null comment '切换成功率 (%)',
    upstream_capacity            bigint       not null comment '小区PDCP层所接收到的上行数据的总吞吐量 (比特)',
    downstream_capacity          bigint       not null comment '小区PDCP层所发送的下行数据的总吞吐量 (比特)',
    rrc_retries                  int          not null comment 'RRC重建请求次数 (无)',
    rrc_retries_rate             float        null comment 'RRC连接重建比率 (%)',
    back_inter_intra_success     int          not null comment '通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)',
    back_inter_inter_success     int          not null comment '通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)',
    back_intra_intra_success     int          not null comment '通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)',
    back_intra_inter_success     int          not null comment '通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)',
    intra_enodeb_success         int          not null comment 'eNB内切换出成功次数 (次)',
    inter_enodeb_success         int          not null comment 'eNB内切换出请求次数 (次)',
    primary key tbkpi_pk (id)
);

create table if not exists tbprb
(
    id                 varchar(32)  not null comment 'ID',
    start_time         varchar(255) not null comment '起始时间',
    enodeb_name        varchar(255) not null comment '网元/基站名称',
    sector_description varchar(255) not null comment '小区描述',
    sector_name        varchar(255) not null comment '小区名',
    noise0             int          not null comment '第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise1             int          not null comment '第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise2             int          not null comment '第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise3             int          not null comment '第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise4             int          not null comment '第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise5             int          not null comment '第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise6             int          not null comment '第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise7             int          not null comment '第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise8             int          not null comment '第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise9             int          not null comment '第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise10            int          not null comment '第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise11            int          not null comment '第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise12            int          not null comment '第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise13            int          not null comment '第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise14            int          not null comment '第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise15            int          not null comment '第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise16            int          not null comment '第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise17            int          not null comment '第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise18            int          not null comment '第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise19            int          not null comment '第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise20            int          not null comment '第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise21            int          not null comment '第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise22            int          not null comment '第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise23            int          not null comment '第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise24            int          not null comment '第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise25            int          not null comment '第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise26            int          not null comment '第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise27            int          not null comment '第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise28            int          not null comment '第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise29            int          not null comment '第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise30            int          not null comment '第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise31            int          not null comment '第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise32            int          not null comment '第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise33            int          not null comment '第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise34            int          not null comment '第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise35            int          not null comment '第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise36            int          not null comment '第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise37            int          not null comment '第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise38            int          not null comment '第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise39            int          not null comment '第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise40            int          not null comment '第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise41            int          not null comment '第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise42            int          not null comment '第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise43            int          not null comment '第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise44            int          not null comment '第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise45            int          not null comment '第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise46            int          not null comment '第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise47            int          not null comment '第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise48            int          not null comment '第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise49            int          not null comment '第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise50            int          not null comment '第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise51            int          not null comment '第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise52            int          not null comment '第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise53            int          not null comment '第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise54            int          not null comment '第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise55            int          not null comment '第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise56            int          not null comment '第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise57            int          not null comment '第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise58            int          not null comment '第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise59            int          not null comment '第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise60            int          not null comment '第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise61            int          not null comment '第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise62            int          not null comment '第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise63            int          not null comment '第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise64            int          not null comment '第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise65            int          not null comment '第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise66            int          not null comment '第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise67            int          not null comment '第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise68            int          not null comment '第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise69            int          not null comment '第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise70            int          not null comment '第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise71            int          not null comment '第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise72            int          not null comment '第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise73            int          not null comment '第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise74            int          not null comment '第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise75            int          not null comment '第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise76            int          not null comment '第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise77            int          not null comment '第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise78            int          not null comment '第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise79            int          not null comment '第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise80            int          not null comment '第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise81            int          not null comment '第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise82            int          not null comment '第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise83            int          not null comment '第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise84            int          not null comment '第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise85            int          not null comment '第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise86            int          not null comment '第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise87            int          not null comment '第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise88            int          not null comment '第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise89            int          not null comment '第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise90            int          not null comment '第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise91            int          not null comment '第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise92            int          not null comment '第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise93            int          not null comment '第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise94            int          not null comment '第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise95            int          not null comment '第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise96            int          not null comment '第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise97            int          not null comment '第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise98            int          not null comment '第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    noise99            int          not null comment '第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)',
    primary key tbprb (id)
);
