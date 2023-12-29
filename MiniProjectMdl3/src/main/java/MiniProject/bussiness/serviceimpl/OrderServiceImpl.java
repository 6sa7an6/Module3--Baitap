package MiniProject.bussiness.serviceimpl;

import MiniProject.bussiness.dao.IOrderDao;
import MiniProject.bussiness.daoimpl.OderDaoImpl;
import MiniProject.bussiness.model.Order;
import MiniProject.bussiness.service.IOrderService;

public class OrderServiceImpl implements IOrderService {
    public static IOrderDao orderDao = new OderDaoImpl();
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }
}
