package model;

import java.io.Serializable;

public class Income implements Serializable {
    public String id;
    public double projected;
    public double actual;

    public Income() {}

    public Income(String id, double projected, double actual) {
        this.id = id;
        this.projected = projected;
        this.actual = actual;
    }

    @Override
    public String toString() {
        return "Projected Income: " + String.format("$%.2f", projected) +
                "\nActual Income: " + String.format("$%.2f", actual);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        Income o = (Income) other;

        return this.id.equals(o.id) && this.projected == o.projected && this.actual == o.actual;
    }
}
