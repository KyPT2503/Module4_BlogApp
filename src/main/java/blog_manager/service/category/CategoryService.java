package blog_manager.service.category;

import blog_manager.model.Category;
import blog_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Category update(int id, Category category) {
        return null;
    }

    @Override
    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findFirstByName(name);
    }
}
