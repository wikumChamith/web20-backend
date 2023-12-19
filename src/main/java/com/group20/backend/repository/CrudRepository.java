package com.group20.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface CrudRepository<T> {

    boolean create(T entity);

    Optional<T> findById(String id) throws ExecutionException, InterruptedException;

    List<T> getAll() throws ExecutionException, InterruptedException;

    T update(T entity);

    void deleteById(String id);
}
