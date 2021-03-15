package blog_manager.controller;

import blog_manager.model.Blog;
import blog_manager.model.Category;
import blog_manager.service.blog.IBlogService;
import blog_manager.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
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
    public ResponseEntity<List<Blog>> getAll() {
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category-search/{id}")
    public ResponseEntity<List<Blog>> getByCategory(@PathVariable("id") Category category) {
        return new ResponseEntity<>(blogService.findByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> create(@RequestBody Blog blog, UriComponentsBuilder uriComponentsBuilder) {
        blogService.add(blog);
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriComponentsBuilder.path("/blog/detail/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Blog> getSingleBlog(@PathVariable("id") Blog blog) {
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateUser(@PathVariable("id") int id, @RequestBody Blog blog) {
        blog.setId(id);
        blogService.update(id, blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") int id) {
        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
