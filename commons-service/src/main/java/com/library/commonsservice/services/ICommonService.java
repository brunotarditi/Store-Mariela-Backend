package com.library.commonsservice.services;

import java.util.List;
import java.util.Optional;

public interface ICommonService<E, TDto> {
    List<TDto> findAll();
    Optional<TDto> findById(Long id);
    E save(TDto entity);
    void deleteById(Long id);
}
