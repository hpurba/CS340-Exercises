package Strategy3;

import Strategy1.Validate;
import Strategy2.SendMessages;

public class Order {
//  private double totalDue = 0.0;
//  private double priceOfItemOrService = 100.00;
//  private double salesTaxRate = 1.08; // 1.08 is the multiplier on the order price. 8% tax
//
//  String[] paymentType = {"credit card", "debit card", "PayPal"};
  int paymentTypeSelected = 0;
  private double paymentTypePremium = 0.0;

  //
//  String[] shippingOptions = {"FedEx", "US Postal Service", "DHL"};
//  int shippingOptionSelected = 0;
//  private double shippingPrice = 10.00;


  IPaymentType paymentType;

  public Order(IPaymentType paymentType) { // }, ISalesTax salesTax, IShippingPrice shippingPrice){
    this.paymentType = paymentType;
//    this.paymentType = paymentType;
  }

//  public SalesTax calculateSalesTax;
//  public void setTaxRate() {
//    calculateSalesTax.setTax();
//  }


  //  public PaymentType calculatePaymentType;
//  public void setPaymentType() {
//    calculatePaymentType.setPayment();
//  }

  //  public ShippingPrice calculateShippingPrice;
//  public void setShippingPrice() {
//    calculateShippingPrice.setShipping();
//  }


//  public double checkoutShoppingCart() {
//    // totalDue = (priceOfItemOrService + shippingPrice) * salesTaxRate + this.paymentType.getPaymentTypePremium();
//    return totalDue;
//  }
}
