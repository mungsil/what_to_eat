package com.fdb.what_to_eat.service

import com.fdb.what_to_eat.domain.Menu
import com.fdb.what_to_eat.repository.CategoryRepository
import com.fdb.what_to_eat.repository.MealTypeRepository
import com.fdb.what_to_eat.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository,
    private val mealTypeRepository: MealTypeRepository
) {

    fun findAll(): List<Menu> = menuRepository.findAll()

    fun findById(id: Long): Menu = menuRepository.findById(id)
        .orElseThrow { NoSuchElementException("메뉴를 찾을 수 없습니다. id=$id") }

    @Transactional
    fun create(name: String, categoryId: Long, mealTypeId: Long): Menu {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { NoSuchElementException("카테고리를 찾을 수 없습니다.") }
        val mealType = mealTypeRepository.findById(mealTypeId)
            .orElseThrow { NoSuchElementException("식사 시간을 찾을 수 없습니다.") }
        return menuRepository.save(Menu(name = name, category = category, mealType = mealType))
    }

    @Transactional
    fun update(id: Long, name: String, categoryId: Long, mealTypeId: Long): Menu {
        val menu = findById(id)
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { NoSuchElementException("카테고리를 찾을 수 없습니다.") }
        val mealType = mealTypeRepository.findById(mealTypeId)
            .orElseThrow { NoSuchElementException("식사 시간을 찾을 수 없습니다.") }
        menu.name = name
        menu.category = category
        menu.mealType = mealType
        menu.updatedAt = java.time.LocalDateTime.now()
        return menu
    }

    @Transactional
    fun delete(id: Long) = menuRepository.deleteById(id)

    fun random(categoryId: Long?, mealTypeId: Long?): Menu? =
        menuRepository.findRandomMenu(categoryId, mealTypeId).firstOrNull()

    fun randomExcluding(mealTypeId: Long, excludeIds: List<Long>): Menu? {
        if (excludeIds.isEmpty()) return random(null, mealTypeId)
        val result = menuRepository.findRandomMenuExcluding(mealTypeId, excludeIds)
        // 제외 후 결과가 없으면(단일 메뉴 케이스) 전체에서 랜덤 추천
        return result.firstOrNull() ?: random(null, mealTypeId)
    }
}
