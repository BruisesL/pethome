package eth.bruises.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 处理redis只能装序列化的值的问题
 * @author bruises
 */
@Configuration
public class RedisConfig {
    /**
     * 注入Redis的连接工厂，可以改变连接工程的相关参数，例如指定序列化器
     */
    @Autowired
    private RedisConnectionFactory factory;

    /**
     * 使用JSON进行序列化
     * Bean注解表示当前方法的返回值需要注入到Spring容器中，并且方法名称就是Bean的name
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        // 1.创建一个RedisTemplate对象并且是Object,Object类型
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 2.把连接工厂设置到这个RedisTemplate对象中，这样就可以通过这个对象获取到Redis连接
        redisTemplate.setConnectionFactory(factory);

        // 3.指定Redis的序列化器
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 4.对除了Hash以外的类型的key/value指定序列化器
        //key的序列化
        redisTemplate.setKeySerializer(genericJackson2JsonRedisSerializer);
        //value的序列化
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

        // 5.对Hash的key/value指定序列化器
        //hash结构key的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash结构value的序列化
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;
    }
}