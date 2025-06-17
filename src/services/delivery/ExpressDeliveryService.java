package services.delivery;

import domain.Order;

public class ExpressDeliveryService implements DeliveryService {
    @Override
    public void deliver(Order order) {
        System.out.println("\n   ДОСТАВКА ЗАКАЗА");
        System.out.println("ЭКСПРЕСС-доставка заказа #" + order.getId());
        System.out.println("Доставим в течение 24 часов!");
        System.out.println("Спасибо за выбор экспресс-доставки!");
    }
}
