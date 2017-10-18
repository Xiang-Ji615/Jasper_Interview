package main.java.inteliment.bo.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages= {"main.java"})
@PropertySource(value= {"classpath:Dao.properties"})
public class BoConfig {

}
