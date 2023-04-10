package com.wang.jk.common.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;

public class Caches {
    private static final CacheManager MGR;
    private static final Cache<String, Object> token;
    private static final Cache<Integer, String> defaultCaches;
    static {
        URL resource = Caches.class.getClassLoader().getResource("ehcache.xml");

        Configuration config = new XmlConfiguration(resource);
        MGR= CacheManagerBuilder.newCacheManager(config);
        MGR.init();
        token = MGR.getCache("token", String.class, Object.class);
        defaultCaches = MGR.getCache("default", Integer.class, String.class);
    }

    public static void putUserToToken(Integer userid,String token){
        defaultCaches.put(userid,token);
    }

    public static void removeUserToToken(Integer userId){
        defaultCaches.remove(userId);
    }
    public static String getUserToToken(Integer userId){
        return defaultCaches.get(userId);
    }
    public static void put(String key,Object value)
    {
        token.put(key,value);
    }

    public static void  remove(String key)
    {
        token.remove(key);
    }

    public static <T> T get(String key)
    {
        return (T)token.get(key);
    }
}
