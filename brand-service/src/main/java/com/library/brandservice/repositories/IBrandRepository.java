package com.library.brandservice.repositories;

import com.library.brandservice.entities.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IBrandRepository extends PagingAndSortingRepository<Brand, Long> {
    @Query(value = "SELECT b FROM Brand b WHERE b.isEnabled = true")
    List<Brand> getBrandsEnabled();
}
