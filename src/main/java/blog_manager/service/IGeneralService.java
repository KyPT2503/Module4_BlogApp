package blog_manager.service;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();

    E findById(int id) throws Exception;

    boolean remove(int id);

    E update(int id, E e);

    E add(E e);
}
