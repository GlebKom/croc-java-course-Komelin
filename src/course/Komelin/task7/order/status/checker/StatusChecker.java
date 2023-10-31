package course.Komelin.task7.order.status.checker;

import course.Komelin.task7.order.status.OrderStatus;

public interface StatusChecker {
    boolean statusCouldBeChanged(OrderStatus oldStatus, OrderStatus newStatus);
}
