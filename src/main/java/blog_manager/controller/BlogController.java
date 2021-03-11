package blog_manager.controller;

import blog_manager.model.Blog;
import blog_manager.model.Category;
import blog_manager.service.blog.IBlogService;
import blog_manager.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> getAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/all")
    public ModelAndView showAll(@PageableDefault(size = 2) Pageable pageable, @RequestParam("name") Optional<String> name) {
        Page<Blog> blogs;
        if (name.isPresent()) {
            blogs = blogService.findByName(name.get(), pageable);
        } else {
            blogs = blogService.findAllForManyPage(pageable);
        }
        return new ModelAndView("index", "blogs", blogs);
    }

    @GetMapping("/category-search/{id}")
    public ModelAndView categorySearch(@PathVariable("id") int categoryId, @PageableDefault(size = 2) Pageable pageable) {
        return new ModelAndView("index", "blogs", blogService.findByCategory(categoryService.findById(categoryId), pageable));
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        return new ModelAndView("create", "blog", new Blog());
    }

    @PostMapping("/create")
    public ModelAndView createBlog(@ModelAttribute("blog") Blog blog) {
        blogService.add(blog);
        return new ModelAndView("redirect:/blog/all");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetailPage(@PathVariable("id") Blog blog) {
        return new ModelAndView("detail", "blog", blog);
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdatePage(@PathVariable("id") int id) {
        return new ModelAndView("create", "blog", blogService.findById(id));
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("blog") Blog blog) {
        blogService.update(blog.getId(), blog);
        return new ModelAndView("redirect:/blog/all");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView showRemovePage(@PathVariable("id") int id) {
        return new ModelAndView("detail", "blog", blogService.findById(id));
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return new ModelAndView("redirect:/blog/all");
    }
}
