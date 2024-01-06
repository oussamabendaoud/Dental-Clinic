package Dao;

import java.util.List;

public interface IDao<T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
