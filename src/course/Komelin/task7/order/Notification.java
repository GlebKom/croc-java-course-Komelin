package course.Komelin.task7.order;

import course.Komelin.task5.appliance.Appliance;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {

    Order order;

    public Notification(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        double price = 0;
        DateTimeFormatter expirationDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ZonedDateTime expirationDate = order.getCreationDateTime().plusWeeks(2);

        StringBuilder notification = new StringBuilder();
        notification.append("Уважаемый %s!\n\n".formatted(order.getName()));
        notification.append("Рады сообщить, что Ваш заказ №%s готов к выдаче.\n\n".formatted(order.getOrderNumber()));
        notification.append("Список заказа:\n\n");
        notification.append("--------------------------------------------\n");

        for (Appliance a : order.getOrderList()) {
            price += a.getPrice();
            notification.append(a.getDescription()).append("\n");
        }

        notification.append("--------------------------------------------\n");
        notification.append("Сумма к оплате: %.2f руб.\n\n".formatted(price));

        notification.append("Срок хранения заказа: %s\n\n".formatted(expirationDateFormatter.format(expirationDate)));
        notification.append("С наилучшими пожеланиями, магазин \"Пионы и питоны\"");

        return notification.toString();
    }
}
