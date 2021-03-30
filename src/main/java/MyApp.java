import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class MyApp {


    public static void main(String[] args) {
        Jedis jedis = RedisConnection.initializeJedis();

        saveString(jedis, "key-ne" , "Value Ne");
        System.out.println(jedis.get("key-ne"));

        saveList(jedis);
        System.out.println(jedis.rpop("this-is-key"));

        Set<String> nicknames = jedis.smembers("nicknames");
        System.out.println(nicknames); // add 3 store 2 because duplicate value

        System.out.println(jedis.sismember("nicknames", "nickname#1")); // true
    }

    private static void saveString(Jedis jedis, String key, String value) {
        jedis.set(key, value);
    }

    private static void saveList(Jedis jedis) {
        jedis.lpush("this-is-key", "Test1");
        jedis.lpush("this-is-key", "Test2");
        jedis.lpush("this-is-key", "Test3");
        jedis.lpush("this-is-key1", "Test4");
        jedis.lpush("this-is-key1", "Test5");
        jedis.lpush("this-is-key1", "Test6");
    }

    private static void saveSet(Jedis jedis){
        jedis.sadd("nicknames", "nickname#1");
        jedis.sadd("nicknames", "nickname#2");
        jedis.sadd("nicknames", "nickname#1"); //will ignore
    }
}
