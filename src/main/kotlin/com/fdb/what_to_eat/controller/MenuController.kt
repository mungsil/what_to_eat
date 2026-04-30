package com.fdb.what_to_eat.controller

import com.fdb.what_to_eat.service.MenuService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/menus")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("menus", menuService.findAll())
        return "menu/list"
    }
}
