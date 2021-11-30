package com.graduate.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceAclMain {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclMain.class, args);
    }

}
