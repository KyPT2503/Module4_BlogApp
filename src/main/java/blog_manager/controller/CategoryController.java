package blog_manager.controller;

import blog_manager.model.Category;
import blog_manager.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        return new ModelAndView("category-create", "category", new Category());
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("category") Category category) {
        categoryService.add(category);
        return new ModelAndView("redirect:/blog/all");
    }
}
