package blog_manager.controller;

import blog_manager.model.Category;
import blog_manager.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Category category) {
        categoryService.add(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") int id, @RequestBody Category category) {
        categoryService.update(id, category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") int id) {
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getSingleCategory(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/name-search/{name}")
    public ResponseEntity<Category> getSingleByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(categoryService.findByName(name), HttpStatus.OK);
    }
}
