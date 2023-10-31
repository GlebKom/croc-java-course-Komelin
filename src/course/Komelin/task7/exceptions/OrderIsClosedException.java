package course.Komelin.task7.exceptions;

import course.Komelin.task7.order.Order;

public class OrderIsClosedException extends RuntimeException{

    Order order;
    public OrderIsClosedException(Order order) {
        this.order = order;
    }

    public OrderIsClosedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Order №%s is already closed".formatted(order.getOrderNumber());
    }
}
