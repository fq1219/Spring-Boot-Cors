package com.example.data;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UserDB {

    public static Cache<String, User> userdb = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();

    static {
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String id3 = UUID.randomUUID().toString();
        userdb.put(id1, new User(id1, "jear"));
        userdb.put(id2, new User(id2, "tom"));
        userdb.put(id3, new User(id3, "jack"));
    }
}
