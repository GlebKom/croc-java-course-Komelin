package course.Komelin.task7.exceptions;

import course.Komelin.task7.order.Order;

public class OrderNotProducedException extends RuntimeException{
    Order order;

    public OrderNotProducedException(Order order) {
        this.order = order;
    }

    @Override
    public String getMessage() {
        return "Order №%s is not produced".formatted(order.getOrderNumber());
    }
}
