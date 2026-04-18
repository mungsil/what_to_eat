package com.fdb.what_to_eat.controller

import com.fdb.what_to_eat.service.MealTypeService
import com.fdb.what_to_eat.service.RecommendService
import com.fdb.what_to_eat.service.VisitorService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalTime

@Controller
class HomeController(
    private val visitorService: VisitorService,
    private val mealTypeService: MealTypeService,
    private val recommendService: RecommendService
) {

    @GetMapping("/")
    fun home(request: HttpServletRequest, response: HttpServletResponse, model: Model): String {
        val visitor = resolveVisitor(request, response)
        val mealTypeOrder = listOf("간식", "아침", "점심", "저녁")
        val mealTypes = mealTypeService.findAll().sortedBy { mealTypeOrder.indexOf(it.name).takeIf { i -> i >= 0 } ?: Int.MAX_VALUE }
        val defaultMealTypeName = when (LocalTime.now().hour) {
            in 5..9   -> "아침"
            in 10..14 -> "점심"
            in 15..20 -> "저녁"
            else      -> "간식"
        }
        model.addAttribute("todayHistories", recommendService.getTodayHistories(visitor))
        model.addAttribute("mealTypes", mealTypes)
        model.addAttribute("defaultMealTypeName", defaultMealTypeName)
        return "index"
    }

    @PostMapping("/recommend")
    fun recommend(
        @RequestParam mealTypeId: Long,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): String {
        val visitor = resolveVisitor(request, response)
        val result = recommendService.recommend(visitor, mealTypeId)
        return if (result == null) "redirect:/?status=noMenu&mealTypeId=$mealTypeId"
        else "redirect:/?status=recommendSuccess&mealTypeId=$mealTypeId"
    }

    private fun resolveVisitor(request: HttpServletRequest, response: HttpServletResponse) =
        run {
            val cookieId = request.cookies?.find { it.name == COOKIE_NAME }?.value
            val visitor = visitorService.getOrCreate(cookieId)
            if (cookieId != visitor.cookieId) {
                val cookie = Cookie(COOKIE_NAME, visitor.cookieId).apply {
                    maxAge = 60 * 60 * 24 * 365
                    path = "/"
                }
                response.addCookie(cookie)
            }
            visitor
        }

    companion object {
        const val COOKIE_NAME = "visitor_id"
    }
}
