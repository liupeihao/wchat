package com.liupeihao.wchat;

import com.liupeihao.wchat.plugin.accesstoken.AccessTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

@SpringBootApplication
public class WchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WchatApplication.class, args);
    }




}
