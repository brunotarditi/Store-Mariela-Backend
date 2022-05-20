package com.library.commonsservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICommonService<E, TDto> {
    List<TDto> findAll();
    Page<TDto> findAll(Pageable pageable);
    Optional<TDto> findById(Long id);
    E save(TDto entity);
    void deleteById(Long id);
}
