package blog_manager.service.blog;

import blog_manager.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BlogService implements IBlogService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        return entityManager.createQuery("select b from Blog as b").getResultList();
    }

    @Override
    public Blog findById(int id) {
        return entityManager.find(Blog.class, id);
    }

    @Override
    public boolean remove(int id) {
        entityManager.remove(entityManager.find(Blog.class, id));
        return true;
    }

    @Override
    public Blog update(int id, Blog blog) {
        entityManager.merge(blog);
        return blog;
    }

    @Override
    public Blog add(Blog blog) {
        entityManager.persist(blog);
        return blog;
    }
}
