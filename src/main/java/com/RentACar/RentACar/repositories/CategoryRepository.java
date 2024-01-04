package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
