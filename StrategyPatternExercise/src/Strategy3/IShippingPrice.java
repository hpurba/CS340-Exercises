package Strategy3;

// getShippingRate gets the additional shipping cost.
public interface IShippingPrice {
  public double getShippingRate();
}

class LessThan500MilesRate implements IShippingPrice {
  public double getShippingRate() {
    return 10.50;
  }
}

class MoreThan500MilesRate implements IShippingPrice {
  public double getShippingRate() {
    return 21.00;
  }
}