package top.westyle.manager;

import com.cxytiandi.encrypt.springboot.annotation.EnableEncrypt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableEncrypt
@SpringBootApplication
@MapperScan("top.westyle.manager.dao.*")
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

}
