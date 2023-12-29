package MiniProject.bussiness.dao;

import MiniProject.bussiness.model.Book;

import java.util.List;

public interface IGenericDao <T , E >{
    List<T> findByWord(E e);
    void save(T t);
    Book findById(E id);
    void delete(E id);
}
