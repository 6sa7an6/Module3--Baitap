package MiniProject.bussiness.service;

import MiniProject.bussiness.model.Order;

public interface IOrderService {
    void save(Order order);
    Order findById(String id);
}
