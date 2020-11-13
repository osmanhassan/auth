package com.example.auth.Repositories;

import java.util.List;
import java.util.Map;

public interface ACLrRepository {

    void save(String entityName,String id, String token);
    Map<String,String> findAll(String entityName);
    String findById(String entityName, String id);
    List<String> findAllByKeys(String entityName, List<String> keys);
    void delete(String entityName, String id);
}
