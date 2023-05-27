package eth.bruises;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author bruises
 */
@SpringBootApplication
@MapperScan("eth.bruises.*.mapper")
@EnableCaching
public class PetHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PetHomeApp.class);
    }
}
