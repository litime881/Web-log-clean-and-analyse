source /etc/profile
hadoop dfs -rm MRProcesslog/*
hadoop dfs -put /root/eclipse/eclipse-workspace/DataProcess/logs/*  /user/root/MRProcesslog
hadoop jar /root/eclipse/eclipse-workspace/DataProcess/DataProcess.jar MRProcesslog MRCleanResult
Drop DataProcess
Drop DataProcessTemp
create table DataProcess(ipnum string, time string, url string, fl float)
    partitioned by (dates string)
    row format delimited
    fields terminated by '\t'
    lines terminated by '\n';

create table DataProcessTemp(dates string, ipnum string, time string, url string, fl float)
    row format delimited
    fields terminated by '\t'
    lines terminated by '\n';
    
load data local inpath '/user/root/MRCleanResult'
    into table DataProcessTemp;

set hive.exec.dynamic.partition.mode=nonstrict;
SET hive.exec.max.dynamic.partitions.pernode = 100000000;

insert overwrite table DataProcess
    partition(dates)
    select dates , ipnum , time , url , fl from DataProcessTemp;

create table DataTohot10iP as select ip from (select ipnum as ip, count(1) as nr from DataProcess where dates ='31/May/2013'  group by ipnum order by nr)as temp limit 10;