package crud.bussiness.dao;

import java.util.List;

public interface IGenericDao <T,E,D>{
    List<T> findAll();
    void add(T t , D name);
    T findById(E id);
    void delete(E id);
    void update(T t);
    List<T> findByName(D name);
}
