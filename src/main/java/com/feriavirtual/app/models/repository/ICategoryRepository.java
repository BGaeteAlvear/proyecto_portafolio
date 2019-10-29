package com.feriavirtual.app.models.repository;

import com.feriavirtual.app.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository  extends JpaRepository<Category, Long> {
}