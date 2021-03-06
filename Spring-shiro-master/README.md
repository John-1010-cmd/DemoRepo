# Spring-shiro
  为了给MIS系统添加一套较为通用的权限控制功能，本项目基于Spring，整合Apache Shiro框架，实现用户管理和权限控制，主要内容如下：
  
  1.登录（带验证码），包括“记住我”的功能；
  
  2.加密，存储的密码不采用明文,初始密码123；
  
  3.session管理：使用shiro默认的session管理替代Tomcat的HttpSession；
  
  4.shiro拦截器：对静态文件（HTML/JS/CSS等）进行权限控制，无权限则请求不到；
  
  5.后台接口权限控制：对后台接口启用权限控制，对应的接口若不满足权限或角色要求，则请求失败；
  
  6.用户-角色-权限使用常规RBAC的模型，用户到角色，角色到权限均为多对多关系映射。
  
  效果图：
 ![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144346_7a596aa2_1110335.jpeg "在这里输入图片标题")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0812/164531_2185e264_1110335.png "1597221895(1).png")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144402_418afcef_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144409_b7aaea2f_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0209/144419_70c4b9f4_1110335.png "在这里输入图片标题")

### 附录：中央技术储备仓库（Central Technique Reserve Repository）

#### 基础篇:职业化，从做好OA系统开始
1. [Spring boot整合Mybatis实现增删改查（支持多数据源）](https://gitee.com/shenzhanwang/SSM)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
2. [Struts2,Hibernate,Spring三大框架的整合实现增删改查](https://gitee.com/shenzhanwang/S2SH)
3. [Spring,SpringMVC和Hibernate的整合实现增删改查](https://gitee.com/shenzhanwang/SSH)
4. [Spring boot整合activiti工作流引擎实现OA开发](https://gitee.com/shenzhanwang/Spring-activiti)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
5. [Spring发布与调用REST风格的WebService](https://gitee.com/shenzhanwang/Spring-REST)
6. [Spring boot整合Axis调用SOAP风格的web服务](https://gitee.com/shenzhanwang/Spring-axis)
7. [Spring boot整合Apache Shiro实现RBAC权限控制](https://gitee.com/shenzhanwang/Spring-shiro)
8. [使用Spring security实现RBAC权限控制](https://gitee.com/shenzhanwang/spring-security-demo)
#### 中级篇：中间件的各种姿势
9. [Spring boot整合mongoDB文档数据库实现增删改查](https://gitee.com/shenzhanwang/Spring-mongoDB)
10. [Spring连接Redis实现缓存](https://gitee.com/shenzhanwang/Spring-redis)
11. [Spring连接图存数据库Neo4j实现增删改查](https://gitee.com/shenzhanwang/Spring-neo4j)
12. Spring boot整合列存数据库hbase实现增删改查
13. [Spring平台整合消息队列ActiveMQ实现发布订阅、生产者消费者模型（JMS）](https://gitee.com/shenzhanwang/Spring-activeMQ)
14. [Spring boot整合消息队列RabbitMQ实现四种消息模式（AMQP）](https://gitee.com/shenzhanwang/Spring-rabbitMQ)
15. Spring boot整合kafka 2.1.0实现大数据消息管道
16. [Spring boot整合websocket实现即时通讯](https://gitee.com/shenzhanwang/Spring-websocket)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
17. [Spring security整合oauth2实现token认证](https://gitee.com/shenzhanwang/Spring-security-oauth2)
18. [Spring boot整合MinIO客户端实现文件管理](https://gitee.com/shenzhanwang/Spring-minio)
19. 23种设计模式，源码、注释、使用场景 
20. [使用ETL工具Kettle的实例](https://gitee.com/shenzhanwang/Kettle-demo)
21. Git指南和分支管理策略 
22. 使用数据仓库进行OLAP数据分析（Mysql+Kettle+Zeppelin）
#### 高级篇：分布式系统和大数据开发
23. [zookeeper原理、架构、使用场景和可视化](https://gitee.com/shenzhanwang/zookeeper-practice)
24. Spring boot整合Apache dubbo v2.7.5实现分布式服务治理（SOA架构） ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题") 
>  包含组件Spring boot v2.2.2+Dubbo v2.7.5+Nacos v1.1.1
<a href="https://images.gitee.com/uploads/images/2020/0114/084731_fd0b7a82_1110335.gif" target="_blank">效果图</a>
25. 使用Spring Cloud Alibaba v2.2.7实现微服务架构（MSA架构）![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg)   
>  包含组件Nacos+Feign+Gateway+Ribbon+Sentinel+Zipkin
<a href="https://images.gitee.com/uploads/images/2020/0106/201827_ac61db63_1110335.gif" target="_blank">效果图</a>
26. 使用jenkins+centos+git+maven搭建持续集成环境自动化部署分布式服务 
27. 使用docker+compose+jenkins+gitlab+spring cloud实现微服务的编排、持续集成和动态扩容 
28. 使用Spark进行分布式计算
- Spark SQL做离线计算
- Spark Streaming做实时计算
- Structured Streaming做实时计算
29. 使用Flink实现流批一体化的分布式计算
30. 搭建高可用nginx集群和Tomcat负载均衡 
31. 使用mycat实现Mysql数据库的主从复制、读写分离、分表分库、负载均衡和高可用 
32. [Spring boot整合Elastic search实现全文检索和大数据分析](https://gitee.com/shenzhanwang/Spring-elastic_search) ![输入图片说明](https://img.shields.io/badge/-%E6%8B%9B%E7%89%8C-yellow.svg "在这里输入图片标题")
#### 特别篇：分布式事务和并发控制
33. 基于可靠消息最终一致性实现分布式事务（activeMQ）
34. Spring boot dubbo整合seata实现分布式事务![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
> 包含组件nacos v2.0.2 + seata v1.4.2 +spring boot dubbo v2.7.5
<a href="https://images.gitee.com/uploads/images/2020/0119/112233_62a33a77_1110335.gif" target="_blank">效果图</a>
35. Spring cloud alibaba v2.2.7整合seata实现分布式事务 ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
> 包含组件nacos v2.0.2 + seata v1.4.2 +spring cloud alibaba v2.2.7
<a href="https://images.gitee.com/uploads/images/2020/0119/134408_ee14a016_1110335.gif" target="_blank">效果图</a>
36. 并发控制：数据库锁机制和事务隔离级别的实现![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题") 
37. 并发控制：使用redission实现分布式锁 
38. 并发控制：使用zookeeper实现分布式锁 
39. 并发控制：Java多线程编程实例
40. 并发控制：使用netty实现高性能NIO通信 
### 视频教程
![输入图片说明](https://images.gitee.com/uploads/images/2021/0115/082443_6e3b60c8_1110335.png "封面.png")

<a href="https://www.roncoo.com/view/1303618857169457154" target="_blank">在线观看（含源码和PPT）</a>
### 关注微信公众号获取更多技术文章和源码
![输入图片说明](https://images.gitee.com/uploads/images/2021/1022/183316_f03b4f99_1110335.jpeg "qrcode_for_gh_4c2318bb0f7f_258.jpg")