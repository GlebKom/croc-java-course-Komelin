package course.Komelin.task7.order;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task7.exceptions.OrderIsClosedException;
import course.Komelin.task7.exceptions.OrderIsExpiredException;
import course.Komelin.task7.exceptions.OrderNotProducedException;
import course.Komelin.task7.order.status.OrderStatus;
import course.Komelin.task7.exceptions.OrderNotCollectedException;

import java.time.ZonedDateTime;
import java.util.List;

public interface Order {

    ZonedDateTime getCreationDateTime();
    ZonedDateTime getCollectionDateTime() throws OrderNotCollectedException;
    ZonedDateTime getProducingDateTime() throws OrderNotProducedException;
    String getPhoneNumber();
    String getClientName();
    String getOrderNumber();
    List<Appliance> getOrderList();
    OrderStatus getOrderStatus();
    void setClientName(String clientName);
    void setDateTimeOffset(int hours);
    void collect();
    void produce();
    boolean isExpired();
    boolean canBeProduced() throws OrderIsExpiredException, OrderIsClosedException;

}
