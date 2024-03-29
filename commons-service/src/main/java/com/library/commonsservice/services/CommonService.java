package com.library.commonsservice.services;

import com.library.commonsservice.factory.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommonService<E, R extends PagingAndSortingRepository<E, Long>, TDto> implements ICommonService<E, TDto>{
    protected final R repository;
    protected final IFactory<E, TDto> iFactory;

    @Autowired
    public CommonService(R repository, IFactory<E, TDto>iFactory) {
        this.repository = repository;
        this.iFactory = iFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TDto> findAll() {
        List<E> entities = (List<E>) repository.findAll();
        return entities
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TDto> findAll(Pageable pageable) {
        Page<E> entities = repository.findAll(pageable);
        List<TDto> dtos = entities
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TDto> findById(Long id) {
        Optional<E> entity =  repository.findById(id);
        return entity
                .stream()
                .map(iFactory::createDto)
                .findFirst();
    }

    @Override
    public E save(TDto tDto) {
        E entity = iFactory.createEntity(tDto);
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
