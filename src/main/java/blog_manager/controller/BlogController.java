package blog_manager.controller;

import blog_manager.model.Blog;
import blog_manager.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/all")
    public ModelAndView showAll() {
        return new ModelAndView("index", "blogs", blogService.findAll());
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
    public ModelAndView showDetailPage(@PathVariable("id") int id) {
        return new ModelAndView("detail", "blog", blogService.findById(id));
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
    public ModelAndView remove(@ModelAttribute("blog") Blog blog){
        blogService.remove(blog.getId());
        return new ModelAndView("redirect:/blog/all");
    }
}
