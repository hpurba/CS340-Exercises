package Strategy3;

// getPaymentTypePremium gets the additional fees associated with the payment type being used.
public interface IPaymentType {
  public double getPaymentTypePremium();
}

class CreditCard implements IPaymentType {
  public double getPaymentTypePremium() {
    return 10.0;
  }
}

class DebitCard implements IPaymentType {
  public double getPaymentTypePremium() {
    return 0.0;
  }
}

class Paypal implements IPaymentType {
  public double getPaymentTypePremium() {
    return 5.0;
  }
}