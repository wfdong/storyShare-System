package jokes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("jokes.dao.mybatismapper")
//@ComponentScan({"smallloanplatform.beans.*", "smallloanplatform.dao.*", "smallloanplatform.services.*", "smallloanplatform.controllers.*"})
//@ComponentScan({"smallloanplatform.*"})
public class Application {

    public static void main(String[] args) {

//        System.setProperty("java.io.tmpdir", "/home/admin/cache/");
//        System.setProperty("java.io.tmpdir", "/home/ubuntu/cache/");
        SpringApplication.run(Application.class, args);
    }
}
