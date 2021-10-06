package pl.abusko.pizzeria;

import org.apache.catalina.filters.WebdavFixFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }


}
