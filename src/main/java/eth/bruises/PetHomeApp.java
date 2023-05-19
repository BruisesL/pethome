package eth.bruises;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author bruises
 */
@SpringBootApplication
@MapperScan("eth.bruises.*.mapper")
public class PetHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PetHomeApp.class);
    }
}
