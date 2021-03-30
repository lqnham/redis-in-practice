import redis.clients.jedis.Jedis;

public final class RedisConnection {
    private static Jedis jedis;

    static{
        connectRedis();
    }

    private static void connectRedis(){
        try {
            jedis = new Jedis("localhost");
            System.out.println("Connection Successful");
            System.out.println("The server is running " + jedis.ping());
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public static Jedis initializeJedis(){
        return jedis;
    }
}
