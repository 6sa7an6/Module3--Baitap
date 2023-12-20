package crud.bussiness.serviceimpl;

import crud.bussiness.dao.IUserDao;
import crud.bussiness.daoimpl.UserDaoImpl;
import crud.bussiness.model.User;
import crud.bussiness.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private static IUserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user, String name) {
        userDao.add(user,name);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
