package texas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//开启Springboot的默认配置
//@EnableAutoConfiguration

@SpringBootApplication
public class TexasApplication {
    public static void main(String[] args) {
        SpringApplication.run(TexasApplication.class,args);
    }
}
