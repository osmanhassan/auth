package com.example.auth.Repositories;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ACLrRepositoryImp implements ACLrRepository {
    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;

    public ACLrRepositoryImp(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(String id, String token) {
        hashOperations.put("ACL", id, token);
    }

    @Override
    public Map<String, String> findAll() {
        return hashOperations.entries("ACL");
    }

    @Override
    public String findById(String id) {
        String token = hashOperations.get("ACL", id) == null ? null : hashOperations.get("ACL", id).toString();
        return token;
    }

    @Override
    public List<String> findAllByKeys(List<String> keys) {
        return hashOperations.multiGet("ACL", keys);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("ACL", id);
    }
}
