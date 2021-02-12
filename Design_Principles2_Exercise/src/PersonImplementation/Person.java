//  1. What design principles does this code violate?
//      Isolated Change Principle. "Avoid shotgun surgery". Also should depend on abstraction more.
//  2. Refactor the code to improve its design.

package PersonImplementation;

abstract class Person {
  public String last;
  public String first;
  public String middle;

  public Person(String last, String first, String middle) {
    this.last = last;
    this.first = first;
    this.middle = middle;
  }
}