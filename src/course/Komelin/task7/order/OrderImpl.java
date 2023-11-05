package course.Komelin.task7.order;

import course.Komelin.task5.appliance.Appliance;
import course.Komelin.task7.exceptions.OrderIsClosedException;
import course.Komelin.task7.exceptions.OrderIsExpiredException;
import course.Komelin.task7.exceptions.OrderNotProducedException;
import course.Komelin.task7.order.status.OrderStatus;
import course.Komelin.task7.order.status.checker.OrderStatusChecker;
import course.Komelin.task7.order.status.checker.StatusChecker;
import course.Komelin.task7.exceptions.OrderNotCollectedException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderImpl implements Order {

    private String clientName;
    private final String phoneNumber;
    private final Instant creationDateTime;
    private Instant collectionDateTime;
    private Instant producingDateTime;
    private final List<Appliance> orderList;
    private String orderNumber;
    OrderStatus orderStatus = OrderStatus.CREATED;
    private int dateTimeOffset;


    public OrderImpl(String name, String phoneNumber, List<Appliance> orderList) {

        // Regex паттерн для номера телефона
        Pattern phoneNumberPattern
                = Pattern.compile("(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))\n");

        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);

        if (phoneNumberMatcher.matches()) {
            throw new IllegalArgumentException("Phone number should be valid");
        }

        if (orderList.size() > 75) {
            throw new IllegalArgumentException("Appliances' count should be less or equals 75");
        }

        this.clientName = name;
        this.orderList = orderList;
        this.phoneNumber = phoneNumber;
        this.creationDateTime = Instant.now();

    }

    @Override
    public ZonedDateTime getCreationDateTime() {
        return creationDateTime.atZone(ZoneId.of(getZoneId()));
    }

    @Override
    public ZonedDateTime getCollectionDateTime() throws OrderNotCollectedException{
        if (orderStatus != OrderStatus.CREATED) {
            return collectionDateTime.atZone(ZoneId.of(getZoneId()));
        } else {
            throw new OrderNotCollectedException(this);
        }
    }

    @Override
    public ZonedDateTime getProducingDateTime() throws OrderNotProducedException{
        if (orderStatus == OrderStatus.CLOSED) {
            return producingDateTime.atZone(ZoneId.of(getZoneId()));
        } else {
            throw new OrderNotProducedException(this);
        }
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getClientName() {
        return clientName;
    }

    @Override
    public String getOrderNumber() {
        if (orderNumber == null) {
            StringBuilder orderNumberBuilder = new StringBuilder();
            ZonedDateTime creationDate = getCreationDateTime();
            String year = String.valueOf(creationDate.getYear());
            String month = String.valueOf(creationDate.getMonthValue());
            String day = String.valueOf(creationDate.getDayOfMonth());
            String hours = String.valueOf(creationDate.getHour());
            String minutes = String.valueOf(creationDate.getMinute());
            String seconds = String.valueOf(creationDate.getSecond());

            orderNumberBuilder.append(year.substring(2));
            orderNumberBuilder.append(month.length() == 1 ? "0" : "").append(month);
            orderNumberBuilder.append(day.length() == 1 ? "0" : "").append(day);
            orderNumberBuilder.append(hours.length() == 1 ? "0" : "").append(hours);
            orderNumberBuilder.append(minutes.length() == 1 ? "0" : "").append(minutes);
            orderNumberBuilder.append(seconds.length() == 1 ? "0" : "").append(seconds);
            orderNumberBuilder.append(phoneNumber.substring(phoneNumber.length() - 4));

            orderNumber = orderNumberBuilder.toString();
        }

        return orderNumber;

    }

    @Override
    public List<Appliance> getOrderList() {
        return new ArrayList<>(orderList);
    }

    @Override
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void setDateTimeOffset(int hours) {
        if (hours < -12 || hours > 14) {
            throw new IllegalArgumentException("Invalid offset : " + hours);
        }
        this.dateTimeOffset = hours;
    }

    @Override
    public void collect(){
        StatusChecker statusChecker = new OrderStatusChecker();
        if (statusChecker.statusCouldBeChanged(orderStatus, OrderStatus.COLLECTED)) {
            collectionDateTime = Instant.now();
            this.orderStatus = OrderStatus.COLLECTED;
        }
    }

    @Override
    public void produce() {
        StatusChecker statusChecker = new OrderStatusChecker();
        if (statusChecker.statusCouldBeChanged(orderStatus, OrderStatus.CLOSED)) {
            producingDateTime = Instant.now();
            this.orderStatus = OrderStatus.CLOSED;
        }
    }

    @Override
    public boolean isExpired() {
        ZonedDateTime creationDateTime = getCreationDateTime();
        if (ZonedDateTime.now(ZoneId.of(getZoneId())).isAfter(creationDateTime.plusWeeks(2))) {
            orderStatus = OrderStatus.EXPIRED;
            return true;
        }
        return false;
    }

    @Override
    public boolean canBeProduced() throws OrderIsExpiredException, OrderIsClosedException{
        switch (orderStatus) {
            case CREATED -> {
                return false;
            }
            case COLLECTED -> {
                if (!isExpired()) {
                    return true;
                } else {
                    throw new OrderIsExpiredException(this);
                }
            }
            case EXPIRED -> {
                throw new OrderIsExpiredException(this);
            }

            case CLOSED -> {
                throw new OrderIsClosedException(this);
            }
        };

        return false;
    }

    private String getZoneId(){
        return "UTC" + (dateTimeOffset >= 0 ? "+" : "") + dateTimeOffset;
    }
}
