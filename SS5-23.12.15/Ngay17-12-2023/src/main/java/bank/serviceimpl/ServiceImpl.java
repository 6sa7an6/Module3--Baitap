package bank.serviceimpl;

import bank.dao.IManagerDao;
import bank.daoimpl.ManagerDaoImpl;
import bank.service.IService;

public class ServiceImpl implements IService {
    IManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public void creat() {
        managerDao.create();
    }

    @Override
    public void read() {
        managerDao.read();
    }

    @Override
    public void update() {
        managerDao.update();
    }

    @Override
    public void delete() {
        managerDao.delete();
    }
}
