package com.springtiny.controller;

import com.springtiny.annotation.Service;
import com.springtiny.annotation.Transaction;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestService {
    @Transaction
    public void helloService(){
        log.info("i am hello service");
    }
}
