package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 启动
     * 1.IDEA运行Application.java
     * 2.mvn spring-boot:run
     * 3.jar包启动
     *   mvn clean package
     *   前台运行 java -jar target/${appName}.jar
     *   后台运行 nohup java -jar target/${appName}.jar &
     * 4.war包启动
     *
     * 停止
     * 1.curl -X POST http://localhost:8080/actuator/shutdown
     * 2.kill -9 [PID]
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
