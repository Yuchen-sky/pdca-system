package top.pdcasystem.pdcasystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PdcaSystemApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public  void testStrings(){
        String redisKey = "test:count";
        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));
    }

    @Test
    public  void testHash(){
        String rediskey = "test:user";

        redisTemplate.opsForHash().put(rediskey, "id", 112);
        redisTemplate.opsForHash().put(rediskey, "username","zhaomengmeng");

        System.out.println(redisTemplate.opsForHash().get(rediskey,"id"));
        System.out.println(redisTemplate.opsForHash().get(rediskey,"username"));
        System.out.println(redisTemplate.opsForHash().get(rediskey,"idddd"));
    }

    @Test
    public void testLists(){
        String redisKey = "test:ids";

        redisTemplate.opsForList().leftPush(redisKey,"hello ");
        redisTemplate.opsForList().leftPush(redisKey, "I ");
        redisTemplate.opsForList().leftPush(redisKey, "am ");
        redisTemplate.opsForList().leftPush(redisKey, "yuchen ");

        System.out.println(redisTemplate.opsForList().size(redisKey));
        System.out.println(redisTemplate.opsForList().index(redisKey, 1));
        System.out.println(redisTemplate.opsForList().range(redisKey, 0, 2));
        System.out.println(redisTemplate.opsForList().rightPop(redisKey));
    }

    @Test
    public  void testzset(){
        String rediskey = "test:stu";

        redisTemplate.opsForZSet().add(rediskey, "三张", 1000);
        redisTemplate.opsForZSet().add(rediskey, "四楼", 800);
        redisTemplate.opsForZSet().add(rediskey, "梦想", 8000);
        redisTemplate.opsForZSet().add(rediskey, "形象", 80);

        System.out.println(redisTemplate.opsForZSet().zCard(rediskey));
        System.out.println(redisTemplate.opsForZSet().score(rediskey, "四楼"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(rediskey, "四楼"));
        System.out.println(redisTemplate.opsForZSet().reverseRange(rediskey, 0,2));
        //System.out.println(redisTemplate.opsForHash().get(rediskey,"idddd"));
    }
}
