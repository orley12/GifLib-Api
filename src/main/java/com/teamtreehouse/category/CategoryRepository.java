package com.teamtreehouse.category;

import com.teamtreehouse.category.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends PagingAndSortingRepository<Category, Long> {

    Category findByName(String name);
}
