/*
 * James Brundege
 * Date: 2018-01-10
 * MIT license: https://opensource.org/licenses/MIT
 */
package jmb.numdesc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run this to start the Spring Boot REST API.
 *
 * By default, the API can be accessed by entering http://localhost:9000/numdesc/111 in a browser, or with a GET
 * request in PostMan, etc. 111 can be any valid integer.
 */
@SpringBootApplication
public class NumberDescriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberDescriberApplication.class, args);
    }
}
