package com.library.commonsservice.factory;

public interface IFactory <E, TDto>{
    E createEntity (TDto tDto);
    TDto createDto (E entity);
}
