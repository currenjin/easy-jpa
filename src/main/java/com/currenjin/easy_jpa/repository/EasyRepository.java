package com.currenjin.easy_jpa.repository;

import java.util.Optional;

public interface EasyRepository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    void deleteById(ID id);
}
