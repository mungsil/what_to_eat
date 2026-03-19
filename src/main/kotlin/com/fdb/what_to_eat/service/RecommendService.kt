package com.fdb.what_to_eat.service

import com.fdb.what_to_eat.domain.RecommendHistory
import com.fdb.what_to_eat.domain.Visitor
import com.fdb.what_to_eat.repository.RecommendHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class RecommendService(
    private val menuService: MenuService,
    private val historyRepository: RecommendHistoryRepository
) {

    @Transactional
    fun recommend(visitor: Visitor, mealTypeId: Long): RecommendHistory? {
        val excludeIds = historyRepository.findByVisitorAndRecommendedAt(visitor, LocalDate.now())
            .map { it.menu.id }
        val menu = menuService.randomExcluding(mealTypeId, excludeIds) ?: return null
        historyRepository.deleteByVisitorAndDateAndMealTypeId(visitor, LocalDate.now(), mealTypeId)
        return historyRepository.save(RecommendHistory(visitor = visitor, menu = menu))
    }

    fun getHistories(visitor: Visitor): List<RecommendHistory> =
        historyRepository.findByVisitorOrderByRecommendedAtDesc(visitor)

    fun getTodayHistories(visitor: Visitor): List<RecommendHistory> =
        historyRepository.findByVisitorAndRecommendedAt(visitor, LocalDate.now())
}
