package com.fdb.what_to_eat.repository

import com.fdb.what_to_eat.domain.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuRepository : JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE (:categoryId IS NULL OR m.category.id = :categoryId) AND (:mealTypeId IS NULL OR m.mealType.id = :mealTypeId) ORDER BY RAND()")
    fun findRandomMenu(categoryId: Long?, mealTypeId: Long?): List<Menu>

    @Query("SELECT m FROM Menu m WHERE m.mealType.id = :mealTypeId AND m.id NOT IN :excludeIds ORDER BY RAND()")
    fun findRandomMenuExcluding(mealTypeId: Long, excludeIds: List<Long>): List<Menu>
}
