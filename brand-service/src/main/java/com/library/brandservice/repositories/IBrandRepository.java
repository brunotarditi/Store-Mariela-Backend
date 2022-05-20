package com.library.brandservice.repositories;

import com.library.brandservice.entities.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBrandRepository extends PagingAndSortingRepository<Brand, Long> {
}
