package com.traiana.cart;


public class ShoppingCartItem {

    private Product product;

    private int quantity;

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }


    public double getTotal() {
        return (this.getQuantity() * product.getPrice().doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShoppingCartItem that = (ShoppingCartItem) o;

        return product.equals(that.product);

    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
               "product=" + product +
               ", quantity=" + quantity +
               '}';
    }
}
