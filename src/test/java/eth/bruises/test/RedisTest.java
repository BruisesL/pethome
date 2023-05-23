package eth.bruises.test;

import eth.bruises.PetHomeApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetHomeApp.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTest() throws Exception {
        redisTemplate.opsForValue().set("age", "1");

        redisTemplate.opsForValue().increment("age",2);

        System.out.println(redisTemplate.opsForValue().get("age"));

        System.out.println("============multiSet&multiGet============");
        Map<String, String> map = new HashMap<>();
        map.put("name","bruises");
        map.put("gender","male");
        redisTemplate.opsForValue().multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("name");
        keys.add("gender");
        List<String> result = redisTemplate.opsForValue().multiGet(keys);
        assert result != null : "redis haven't save these keys";
        result.forEach(System.out::println);

        System.out.println("==========getKeys==========");
        for (String key : Objects.requireNonNull(redisTemplate.keys("*"))) {
            System.out.println(key);
        }
        // 清空数据库
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushAll();
                return null;
            }
        });

    }
}
