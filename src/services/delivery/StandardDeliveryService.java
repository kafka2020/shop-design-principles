package services.delivery;

import domain.Order;

public class StandardDeliveryService implements DeliveryService {
    @Override
    public void deliver(Order order) {
        System.out.println("\n   ДОСТАВКА ЗАКАЗА");
        System.out.println("Стандартная доставка заказа #" + order.getId());
        System.out.println("Ожидайте доставку в течение 3-5 дней");
        System.out.println("Спасибо за покупку!");
    }
}
