package com.fdb.what_to_eat.controller

import com.fdb.what_to_eat.service.CategoryService
import com.fdb.what_to_eat.service.MealTypeService
import com.fdb.what_to_eat.service.MenuService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/menus")
class MenuController(
    private val menuService: MenuService,
    private val categoryService: CategoryService,
    private val mealTypeService: MealTypeService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("menus", menuService.findAll())
        return "menu/list"
    }

    @GetMapping("/new")
    fun newForm(model: Model): String {
        model.addAttribute("categories", categoryService.findAll())
        model.addAttribute("mealTypes", mealTypeService.findAll())
        return "menu/form"
    }

    @PostMapping
    fun create(
        @RequestParam name: String,
        @RequestParam categoryId: Long,
        @RequestParam mealTypeId: Long
    ): String {
        menuService.create(name, categoryId, mealTypeId)
        return "redirect:/menus"
    }

    @GetMapping("/{id}/edit")
    fun editForm(@PathVariable id: Long, model: Model): String {
        model.addAttribute("menu", menuService.findById(id))
        model.addAttribute("categories", categoryService.findAll())
        model.addAttribute("mealTypes", mealTypeService.findAll())
        return "menu/form"
    }

    @PostMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestParam name: String,
        @RequestParam categoryId: Long,
        @RequestParam mealTypeId: Long
    ): String {
        menuService.update(id, name, categoryId, mealTypeId)
        return "redirect:/menus"
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable id: Long): String {
        menuService.delete(id)
        return "redirect:/menus"
    }
}
