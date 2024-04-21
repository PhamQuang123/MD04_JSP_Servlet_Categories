package ra.jsp_servlet_categories.repository;

import java.util.List;

public interface Repository<E, T> {
    List<E> findAll();

    boolean save(E e);

    boolean update(E e);

    E findElementById(T id);

    boolean remove(T id);
}
