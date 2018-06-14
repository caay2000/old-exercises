package pl.refactoring.interpreter;

public class Product {
    final int id;
    final ProductColor color;
    final String name;
    final ProductSize size;
    final float price;

    public Product(String name, ProductSize size, float price, int id, ProductColor color) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.id = id;
        this.color = color;
    }

    public int getID() {
        return id;
    }

    public ProductSize getSize() {
        return size;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public ProductColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.getPrice() + " - " + this.getColor();
    }
}
