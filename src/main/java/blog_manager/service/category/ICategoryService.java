package blog_manager.service.category;

import blog_manager.model.Category;
import blog_manager.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    Category findByName(String name);
}
