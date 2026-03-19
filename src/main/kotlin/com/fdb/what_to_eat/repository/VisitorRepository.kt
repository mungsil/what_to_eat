package com.fdb.what_to_eat.repository

import com.fdb.what_to_eat.domain.Visitor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface VisitorRepository : JpaRepository<Visitor, Long> {
    fun findByCookieId(cookieId: String): Optional<Visitor>
}
