package com.fdb.what_to_eat.controller

import com.fdb.what_to_eat.service.RecommendService
import com.fdb.what_to_eat.service.VisitorService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/histories")
class HistoryController(
    private val visitorService: VisitorService,
    private val recommendService: RecommendService
) {

    @GetMapping
    fun list(request: HttpServletRequest, response: HttpServletResponse, model: Model): String {
        val cookieId = request.cookies?.find { it.name == HomeController.COOKIE_NAME }?.value
        val visitor = visitorService.getOrCreate(cookieId)
        model.addAttribute("histories", recommendService.getHistories(visitor))
        return "history/list"
    }
}
