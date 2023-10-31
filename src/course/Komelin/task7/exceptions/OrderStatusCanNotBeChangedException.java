package course.Komelin.task7.exceptions;

import course.Komelin.task7.order.status.OrderStatus;

public class OrderStatusCanNotBeChangedException extends RuntimeException{
    OrderStatus oldStatus;
    OrderStatus newStatus;
    public OrderStatusCanNotBeChangedException(OrderStatus oldStatus, OrderStatus newStatus) {
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public OrderStatusCanNotBeChangedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Order status can't be changed to %s because it's already %s".formatted(newStatus.toString(),
                oldStatus.toString());
    }
}
