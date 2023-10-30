package hello.core.Order;

public interface OrderService {
    Order creatOrder(Long memeberId, String itemName, int itemPrice);
}
