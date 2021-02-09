package Strategy3;

//  String[] paymentType = {"credit card", "debit card", "PayPal"};
public interface IPaymentType {
  public double getPaymentTypePremium();
}

class CreditCard implements IPaymentType {
  public double getPaymentTypePremium() {
    return 0.0;
  }
}

class Paypal implements IPaymentType {
  public double getPaymentTypePremium() {
    return 10.0;
  }
}