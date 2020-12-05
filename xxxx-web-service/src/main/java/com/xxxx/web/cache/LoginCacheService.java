package com.xxxx.web.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "cache:token-user")
public class LoginCacheService {


//
//    @Cacheable(key = "#token", unless = "#result == null")
//    public UserVo getUserByToken(String token) {
//        return null;
//    }
//
//
//    @CachePut(key = "#token", unless = "#result == null")
//    public UserVo updateUserByToken(String token, UserVo userVo) {
//
//        return userVo;
//    }
//
//    @CacheEvict(key = "#token")
//    public int deleteTokenUser(String token) {
//        return 0;
//    }
}
