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
    public void save(String entityName, String id, String token) {
        hashOperations.put(entityName, id, token);
    }

    @Override
    public Map<String, String> findAll(String entityName) {
        return hashOperations.entries(entityName);
    }

    @Override
    public String findById(String entityName, String id) {
        String token = hashOperations.get(entityName, id) == null ? null : hashOperations.get(entityName, id).toString();
        return token;
    }

    @Override
    public List<String> findAllByKeys(String entityName, List<String> keys) {
        return hashOperations.multiGet(entityName, keys);
    }

    @Override
    public void delete(String entityName,String id) {
        hashOperations.delete(entityName, id);
    }
}
