package blog_manager.concern;

import blog_manager.model.Blog;
import org.aspectj.lang.annotation.*;

@Aspect
public class Logger {
    @AfterReturning(pointcut = "execution(public * blog_manager.service.blog.BlogService.findById(..))", returning = "blog")
    public void log(Blog blog) {
        System.out.println("[Logger] ...");
        System.out.println("Find by id: " + "...");
        System.out.println("Result: " + blog.getTitle());
    }
    /*
    *@After(value = "execution(public * blog_manager.service.blog.BlogService.add(blog))", argNames = "blog")
    public void logAdd(Blog blog){
        System.out.println("[Logger] ...");
        System.out.println("BlogService.add() method called.");
        System.out.println(blog.getTitle());
    }*/
}
