package com.fdb.what_to_eat.repository

import com.fdb.what_to_eat.domain.MealType
import org.springframework.data.jpa.repository.JpaRepository

interface MealTypeRepository : JpaRepository<MealType, Long>
