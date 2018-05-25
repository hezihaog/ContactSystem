# ContactSystem
联系人系统接口。

1、使用时，请在Tomcat的server.xml中配置URIEncoding为utf-8，本项目不再对get方法请求做处理
2、否则传递中文信息时会乱码

```xml
<Connector port="8080" protocol="HTTP/1.1" 
               connectionTimeout="20000"
               URIEncoding="UTF-8"
               redirectPort="8443" />
```