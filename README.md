# Web-log-clean-and-analyse
### A Simple project to analyse big data with Hadoop
##### 通过下载得到网络相关日志，该日志由原网站每日可产生50~100 万条数据，日志内容包括：ip， 时间， 浏览链接， HTTP请求状态码， 访问流量，通过Hadoop分布式框架分析数据，比如每日浏览量， 每日最热门IP，包括一些特殊指标，包括估算注册人数量等等。通过挖掘导入数据库中。
+本上传只针对数据整理和分析：
+1. 将数据上传至hdfs 中，通过Java编写mapreduce 处理相关的文件，将正确且需要的数据如IP， 浏览链接等抓到reduce中output至hdfs中。编写完成后将src 导出jar文件。
+2. 配置crontab定时跑shell文件，编辑该文件，将Jar设为定时跑相关文件。
+3. 通过Hive，将hdfs文件导入到Hive创建的分区表中，表以日期作为分区。查询相关业务数据并添加在相关的业务表里（可以通过sqoop导入MySQL在页面上读取显示）。
