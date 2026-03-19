package com.fdb.what_to_eat.service

import com.fdb.what_to_eat.domain.MealType
import com.fdb.what_to_eat.repository.MealTypeRepository
import org.springframework.stereotype.Service

@Service
class MealTypeService(private val mealTypeRepository: MealTypeRepository) {
    fun findAll(): List<MealType> = mealTypeRepository.findAll()
}
