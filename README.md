# java-sdk-demo

java-sdk-demo 是 asbamboo/java-sdk 使用示例， demo的目的是演示如何和使用 asbamboo/java-sdk 调用 http://api.asbamboo.com 中的各个接，所以demo中逻辑处理很松散。

实际应用程序开发时，需要根据实际应用程序的需要做程序自身的逻辑处理。

# 开发说明

java-sdk-demo 是java se 1.8开发。

java-sdk-demo 的版本编号会保持与java-sdk的版本号一致，除了最后一位，bug处理的编号。
 - 如 java-sdk1.0.0 的 demo 可以是 java-sdk-demo1.0.0或者 java-sdk-demo1.0.1 等等
 - 如 java-sdk1.1.0 的 demo 可以是 java-sdk-demo1.1.0或者 java-sdk-demo1.1.1 等等

java-sdk-demo 程序基于 https://github.com/spring-guides/gs-serving-web-content 开发, 请参考springboot相关文档

# 参数配置

为运行 java-sdk-demo，你首先需要做好参数的配置,修改配置文件 `java-sdk-demo/src/main/java/asbamboo/Configure.java` 中的几个属性

```
user@~:~/java-sdk-demo/src/main/java/asbamboo$ cat Configure.java 
package asbamboo;

import java.io.File;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月24日
 */
public class Configure 
{
	public static String API_URL = "http://developer.asbamboo.com/api"; //目前聚合平台有开发和正式两种环境。
	
	public static String API_APP_KEY = "你的应用的App key";
	
	public static String API_SECRET = "你的应用的 app SECRET"
 
 ...
}

```
如果你还没有app key与app secrect 请前往 http://www.asbamboo.com 配置。



# java-sdk-demo 运行

在linux下，需要在 java-sdk-demo 工作目录下运行如下命令启动web服务, 然后在浏览器中运行 http://127.0.0.1:8080:

```
user@～:/java/java-sdk-demo$ ./gradlew build
Starting a Gradle Daemon, 1 busy Daemon could not be reused, use --status for details

BUILD SUCCESSFUL in 19s
3 actionable tasks: 2 executed, 1 up-to-date
user@~:/java/java-sdk-demo$ ./gradlew bootRun

> Task :bootRun 
20:59:48.128 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Included patterns for restart : []
20:59:48.130 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Excluded patterns for restart : [/spring-boot-starter-[\w-]+/, /spring-boot/target/classes/, /spring-boot-starter/target/classes/, /spring-boot-devtools/target/classes/, /spring-boot-actuator/target/classes/, /spring-boot-autoconfigure/target/classes/]
20:59:48.131 [main] DEBUG org.springframework.boot.devtools.restart.ChangeableUrls - Matching URLs for reloading : [file:/java/java-sdk-demo/build/classes/java/main/, file:/java/java-sdk-demo/build/resources/main/]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.5.RELEASE)
```

如果在windows中运行 java-sdk-demo 请参考 https://docs.gradle.org/current/userguide/installation.html#microsoft_windows_users


