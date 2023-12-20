package crud.bussiness.service;

import crud.bussiness.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    void add(User t , String name);
    User findById(Long id);
    void delete(Long id);
    void update(User user);
    List<User> findByName(String name);
}
