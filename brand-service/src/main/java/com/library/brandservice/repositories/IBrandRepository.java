package com.library.brandservice.repositories;

import com.library.brandservice.entities.Brand;
import org.springframework.data.repository.CrudRepository;

public interface IBrandRepository extends CrudRepository<Brand, Long> {
}
