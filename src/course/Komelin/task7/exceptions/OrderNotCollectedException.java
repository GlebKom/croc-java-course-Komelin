package course.Komelin.task7.exceptions;

import course.Komelin.task7.order.Order;

public class OrderNotCollectedException extends RuntimeException{
    Order order;

    public OrderNotCollectedException(Order order) {
        this.order = order;
    }

    @Override
    public String getMessage() {
        return "Order №%s is not collected".formatted(order.getOrderNumber());
    }
}
