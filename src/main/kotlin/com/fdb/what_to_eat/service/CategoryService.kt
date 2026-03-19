package com.fdb.what_to_eat.service

import com.fdb.what_to_eat.domain.Category
import com.fdb.what_to_eat.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun findAll(): List<Category> = categoryRepository.findAll()
}
