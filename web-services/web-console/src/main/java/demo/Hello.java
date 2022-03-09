package demo;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {

    @WebMethod
    public String hello() {
        return "Hello World";
    }
}
