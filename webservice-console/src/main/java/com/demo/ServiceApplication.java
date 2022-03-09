package com.demo;

import javax.xml.ws.Endpoint;

public class ServiceApplication {

    public static void main(String[] args) {
        String bindindUrl = "http://localhost:9898/md5WebService";
        DemoWebservice demoWebservice = new DemoWebservice();
        Endpoint.publish(bindindUrl, demoWebservice);
        System.out.println("Server started at: " + bindindUrl);
    }
}
