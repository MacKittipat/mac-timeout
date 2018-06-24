package com.mackittipat.macserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @RequestMapping("/server")
    public String server() throws InterruptedException {

        Thread.sleep(60000);

        return "server";
    }
}
