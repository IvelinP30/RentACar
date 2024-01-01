package com.RentACar.RentACar.repositories;

import com.RentACar.RentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
