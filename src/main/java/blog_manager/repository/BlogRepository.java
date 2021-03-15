package blog_manager.repository;

import blog_manager.model.Blog;
import blog_manager.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer> {
    List<Blog> findAllByTitleContaining(String title);

    List<Blog> findAllByCategoryId(int categoryId);
}
