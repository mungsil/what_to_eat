package com.fdb.what_to_eat.repository

import com.fdb.what_to_eat.domain.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>
