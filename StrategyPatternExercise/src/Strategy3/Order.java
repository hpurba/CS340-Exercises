package Strategy3;

/**
 * 3. E-commerce applications need to process online customer orders. The basic process for processing an order is the
 *    same for all applications, and includes steps like calculating sales tax, taking payment, and shipping the order.
 *    However, each of these steps will be somewhat different for each application:
 *  1. The algorithm for calculating sales tax will depend on the laws of the state and/or country in which the software is deployed
 *  2. There are many different ways that a customer can pay: credit card, debit card, PayPal, etc.
 *  3. There are many different shipping options: FedEx, US Postal Service, DHL, etc.
 */
public class Order {
  private double totalDue = 0.0;
  private double priceOfItemOrService = 0.0;

  IPaymentType paymentType;
  ISalesTax salesTax;
  IShippingPrice shippingPrice;

  public void setPriceOfItemOrService(double priceOfItemOrService) {
    this.priceOfItemOrService = priceOfItemOrService;
  }

  public Order(IPaymentType paymentType, ISalesTax salesTax, IShippingPrice shippingPrice){
    this.paymentType = paymentType;
    this.salesTax = salesTax;
    this.shippingPrice = shippingPrice;
  }

  public double checkoutShoppingCart() {
    totalDue = (priceOfItemOrService + this.shippingPrice.getShippingRate()) * this.salesTax.getSalesTaxRate() + this.paymentType.getPaymentTypePremium();
    return totalDue;
  }
}