package course.Komelin.task7.order.status.checker;

import course.Komelin.task7.exceptions.OrderStatusCanNotBeChangedException;
import course.Komelin.task7.order.status.OrderStatus;

public class OrderStatusChecker implements StatusChecker {

    public boolean statusCouldBeChanged(OrderStatus oldStatus, OrderStatus newStatus) {
        if (newStatus.compareTo(oldStatus) > 0) {
            return true;
        } else {
            throw new OrderStatusCanNotBeChangedException(oldStatus, newStatus);
        }
    }
}
