package com.fdb.what_to_eat

import com.fdb.what_to_eat.domain.Category
import com.fdb.what_to_eat.domain.MealType
import com.fdb.what_to_eat.repository.CategoryRepository
import com.fdb.what_to_eat.repository.MealTypeRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val categoryRepository: CategoryRepository,
    private val mealTypeRepository: MealTypeRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        if (categoryRepository.count() == 0L) {
            listOf("한식", "중식", "일식", "양식", "분식").forEach {
                categoryRepository.save(Category(name = it))
            }
        }
        if (mealTypeRepository.count() == 0L) {
            listOf("아침", "점심", "저녁", "간식").forEach {
                mealTypeRepository.save(MealType(name = it))
            }
        }
    }
}
