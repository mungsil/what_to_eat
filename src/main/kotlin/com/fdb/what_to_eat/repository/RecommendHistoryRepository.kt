package com.fdb.what_to_eat.repository

import com.fdb.what_to_eat.domain.RecommendHistory
import com.fdb.what_to_eat.domain.Visitor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface RecommendHistoryRepository : JpaRepository<RecommendHistory, Long> {
    fun findByVisitorOrderByRecommendedAtDesc(visitor: Visitor): List<RecommendHistory>
    fun findByVisitorAndRecommendedAt(visitor: Visitor, recommendedAt: LocalDate): List<RecommendHistory>
    fun deleteByVisitorAndRecommendedAt(visitor: Visitor, recommendedAt: LocalDate)

    @Modifying
    @Query("DELETE FROM RecommendHistory h WHERE h.visitor = :visitor AND h.recommendedAt = :date AND h.menu.mealType.id = :mealTypeId")
    fun deleteByVisitorAndDateAndMealTypeId(visitor: Visitor, date: LocalDate, mealTypeId: Long)
}
