package course.Komelin.task7.exceptions;

import course.Komelin.task7.order.Order;

public class OrderIsExpiredException extends RuntimeException{
    Order order;
    public OrderIsExpiredException(Order order) {
        this.order = order;
    }

    public OrderIsExpiredException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Order №%s is expired".formatted(order.getOrderNumber());
    }
}
