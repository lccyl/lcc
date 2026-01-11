package serviceorder.service;

import model.order.bean.Order;

public interface OrderService {
    Order createOrder(Order order);
}
