package com.shop.admin.category;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.shop.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{

}
