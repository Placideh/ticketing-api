package com.placideh.ticketingapi.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constants {
    @Value("${jwt-secret}")
    public  static  String API_SECRET_KEY="secret";

    @Value("${token-validity}")
    public  static long TOKEN_VALIDITY=2*60*60*100;


}
