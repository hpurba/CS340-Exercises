package Strategy3;

// getSalesTaxRate gets the taxRate as a percentage. ex. 1.08 being 8% tax.
public interface ISalesTax {
  public double getSalesTaxRate();
}

class EasternStates implements ISalesTax {
  public double getSalesTaxRate() {
    return 1.12;
  }
}

class WesternStates implements ISalesTax {
  public double getSalesTaxRate() {
    return 1.08;
  }
}
