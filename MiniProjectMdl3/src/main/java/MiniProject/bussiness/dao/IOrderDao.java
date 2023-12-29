package MiniProject.bussiness.dao;

import MiniProject.bussiness.model.Order;

public interface IOrderDao {
    void save(Order order);
    Order findById(String id);
}
