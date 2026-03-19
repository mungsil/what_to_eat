package com.fdb.what_to_eat.service

import com.fdb.what_to_eat.domain.Visitor
import com.fdb.what_to_eat.repository.VisitorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class VisitorService(private val visitorRepository: VisitorRepository) {

    @Transactional
    fun getOrCreate(cookieId: String?): Visitor {
        if (cookieId != null) {
            val visitor = visitorRepository.findByCookieId(cookieId).orElse(null)
            if (visitor != null) {
                visitor.lastVisitAt = LocalDateTime.now()
                return visitor
            }
        }
        return visitorRepository.save(Visitor())
    }
}
