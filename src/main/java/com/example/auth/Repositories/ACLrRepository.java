package com.example.auth.Repositories;

import java.util.List;
import java.util.Map;

public interface ACLrRepository {

    void save(String id, String token);
    Map<String,String> findAll();
    String findById(String id);
    List<String> findAllByKeys(List<String> keys);
    void delete(String id);
}
