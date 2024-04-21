package ra.jsp_servlet_categories.service;

import java.util.List;

public interface Service<E, T> {
    List<E> findAll();

    boolean save(E e);

    E findElementById(T id);

    boolean update(E e);

    boolean remove(T id);
}
