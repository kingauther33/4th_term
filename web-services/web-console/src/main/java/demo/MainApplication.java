package demo;

import javax.xml.ws.Endpoint;

public class MainApplication {
    public static void main(String[] args) {
        Hello hello = new Hello();
        Endpoint.publish("http://localhost:9999/helloService", hello);
        System.out.println("Service started.");
    }
}
