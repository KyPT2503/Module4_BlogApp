package blog_manager.service.blog;

import blog_manager.model.Blog;
import blog_manager.model.Category;
import blog_manager.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService extends IGeneralService<Blog> {
    Page<Blog> findAllForManyPage(Pageable pageable);

    List<Blog> findByName(String name);

    List<Blog> findByCategory(Category category);
}
