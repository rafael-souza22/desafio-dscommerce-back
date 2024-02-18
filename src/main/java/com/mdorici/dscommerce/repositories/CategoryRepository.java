package com.mdorici.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdorici.dscommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {
	
}
