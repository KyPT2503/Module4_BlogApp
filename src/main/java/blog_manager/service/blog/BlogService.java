package blog_manager.service.blog;

import blog_manager.model.Blog;
import blog_manager.model.Category;
import blog_manager.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public Blog findById(int id) throws Exception {
        if (!blogRepository.exists(id)) throw new Exception();
        return blogRepository.findOne(id);
    }

    @Override
    public boolean remove(int id) {
        blogRepository.delete(id);
        return true;
    }

    @Override
    public Blog update(int id, Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog add(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findAllForManyPage(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findByName(String name, Pageable pageable) {
        return (Page<Blog>) blogRepository.findAllByTitleContaining(name, pageable);
    }

    @Override
    public Page<Blog> findByCategory(Category category, Pageable pageable) {
        return blogRepository.findAllByCategoryId(category.getId(), pageable);
    }
}
