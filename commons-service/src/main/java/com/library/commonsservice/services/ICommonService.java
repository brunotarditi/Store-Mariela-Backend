package com.library.commonsservice.services;

import java.util.List;
import java.util.Optional;

public interface ICommonService<E> {
    List<E> findAll();
    Optional<E> findById(Long id);
    E save(E entity);
    void deleteById(Long id);
}
