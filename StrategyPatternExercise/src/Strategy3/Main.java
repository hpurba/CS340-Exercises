package Strategy3;

public class Main {
  public static void main(String[] args){
    // PaymentType, SalesTax, ShippingPrice
    Order myFirstOrderCheap = new Order(new DebitCard(), new WesternStates(), new LessThan500MilesRate());
    myFirstOrderCheap.setPriceOfItemOrService(100.00);
    System.out.println("Order Price is: $" + myFirstOrderCheap.checkoutShoppingCart());

    Order mySecondOrderExpensive = new Order(new CreditCard(), new EasternStates(), new MoreThan500MilesRate());
    mySecondOrderExpensive.setPriceOfItemOrService(100.00);
    System.out.println("Order Price is: $" + mySecondOrderExpensive.checkoutShoppingCart());
  }
}