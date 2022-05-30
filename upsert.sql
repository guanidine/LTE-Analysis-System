drop table if exists tbCell;
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

drop function tbcell_upsert;
create or replace function tbcell_upsert() returns trigger as $$
begin
update tbcell
set city = new.city,sector_id = new.sector_id,sector_name = new.sector_name,
    enodebid = new.enodebid,enodeb_name = new.enodeb_name,earfcn = new.earfcn,
    pci = new.pci,pss = new.pss,sss = new.sss,tac = new.tac,vendor = new.vendor,
    longitude = new.longitude,latitude = new.latitude,style = new.style,
    azimuth = new.azimuth,height = new.height,electtilt = new.electtilt,
    mechtilt = new.mechtilt,totletilt = new.totletilt
where sector_id = new.sector_id;
if found then
			return null;
end if;
return new;
end
$$ language plpgsql;

drop trigger if exists tbcell_upsert_trigger on tbcell;
create trigger tbcell_upsert_trigger before insert on tbcell
    for each row execute procedure tbcell_upsert();

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

drop function if exists tbkpi_upsert;
create or replace function tbkpi_upsert() returns trigger as
$$
begin
update tbkpi
set sector_description           = new.sector_description,
    rrc_success                  = new.rrc_success,
    rrc_tries                    = new.rrc_tries,
    rrc_success_rate             = new.rrc_success_rate,
    erab_success                 = new.erab_success,
    erab_tries                   = new.erab_tries,
    erab_success_rate            = new.erab_success_rate,
    enodeb_erab_release          = new.enodeb_erab_release,
    sector_erab_release          = new.sector_erab_release,
    erab_drop_rate               = new.erab_drop_rate,
    wireless_connection_rate     = new.wireless_connection_rate,
    enodeb_ue_release            = new.enodeb_ue_release,
    ue_release                   = new.ue_release,
    ue_success                   = new.ue_success,
    wireless_drop_rate           = new.wireless_drop_rate,
    intra_inter_success          = new.intra_inter_success,
    intra_inter_tries            = new.intra_inter_tries,
    intra_intra_success          = new.intra_intra_success,
    intra_intra_tries            = new.intra_intra_tries,
    inter_inter_success          = new.inter_inter_success,
    inter_inter_tries            = new.inter_inter_tries,
    inter_intra_success          = new.inter_intra_success,
    inter_intra_tries            = new.inter_intra_tries,
    intra_enodeb_success_rate    = new.intra_enodeb_success_rate,
    inter_enodeb_success_rate    = new.inter_enodeb_success_rate,
    intra_frequency_success_rate = new.intra_frequency_success_rate,
    inter_frequency_success_rate = new.inter_frequency_success_rate,
    handover_success_rate        = new.handover_success_rate,
    upstream_capacity            = new.upstream_capacity,
    downstream_capacity          = new.downstream_capacity,
    rrc_retries                  = new.rrc_retries,
    rrc_retries_rate             = new.rrc_retries_rate,
    back_inter_intra_success     = new.back_inter_intra_success,
    back_inter_inter_success     = new.back_inter_inter_success,
    back_intra_intra_success     = new.back_intra_intra_success,
    back_intra_inter_success     = new.back_intra_inter_success,
    intra_enodeb_success         = new.intra_enodeb_success,
    inter_enodeb_success         = new.inter_enodeb_success
where start_time = new.start_time
  and enodeb_name = new.enodeb_name
  and sector_name = new.sector_name;
if found then
        return null;
end if;
return new;
end
$$ language plpgsql;
drop trigger if exists tbkpi_upsert_trigger on tbkpi;
create trigger tbkpi_upsert_trigger
    before insert
    on tbkpi
    for each row
    execute procedure tbkpi_upsert();