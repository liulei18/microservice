# microservice

* RabbitMQ http://mini7:15672/

* lenovo-microservice-eureka http://mini7:6868/

* lenovo-microservice-config-server http://mini7:6688/microservice-dev.properties

* lenovo-microservice-api-gateway http://mini7:6677/item-service/item/2?token=afsdfwesdfaljfafdjladjfabvand

* lenovo-microservice-item http://mini7:8181/item/1

* lenovo-microservice-order http://mini7:8082/order/59193738268961441

## 验证配置文件动态刷新 (Spring Cloud Bus整合RabbitMQ)
1. 修改https://github.com/liulei18/lenovo-config-server/blob/master/microservice-dev.properties配置文件,并且提交到github
2. github配置Webhoks
3. 访问 http://mini7:8181/test http://mini7:8281/test 看配置文件是否改变
