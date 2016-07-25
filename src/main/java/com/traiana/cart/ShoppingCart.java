package com.traiana.cart;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ShoppingCart {

    private static final Logger logger = org.apache.log4j.Logger.getLogger(ShoppingCart.class);

    private List<ShoppingCartItem> items = new ArrayList<>();

    public ShoppingCart() {
        logger.info("Create new shopping cart");
    }

    public boolean isExist(Product product) {
        final Optional<ShoppingCartItem> cartItem = items.stream().filter(scItem -> scItem.getProduct().getId().equals(product.getId())).findFirst();
        return cartItem.isPresent();
    }

    public Optional<ShoppingCartItem> getItemByProduct(Product product) {
        return items.stream().filter(scItem -> scItem.getProduct().getId().equals(product.getId())).findFirst();
    }

    /**
     * Adds a <code>ShoppingCartItem</code> to the <code>ShoppingCart</code>'s <code>items</code> list. If item of the specified <code>product</code> already exists in
     * shopping cart list, the quantity of that item is incremented.
     *
     * @param product the <code>Product</code> that defines the type of shopping cart item
     * @see ShoppingCartItem
     */
    public ShoppingCartItem addItem(Product product) {
        logger.info("adding new item to cart, product: " + product);
        ShoppingCartItem shoppingCartItem;
        // check if item already exist
        final Optional<ShoppingCartItem> cartItem = items.stream().filter(item -> item.getProduct().getId().equals(product.getId())).findFirst();

        if (cartItem.isPresent()) {
            // if exist - update quantity
            shoppingCartItem = cartItem.get();
            shoppingCartItem.incrementQuantity();
        } else {
            // if not exist - add
            shoppingCartItem = new ShoppingCartItem(product);
            items.add(shoppingCartItem);
        }
        return shoppingCartItem;
    }

    /**
     * Updates the <code>ShoppingCartItem</code> of the specified <code>product</code> to the specified quantity. If '<code>0</code>' is the given quantity, the
     * <code>ShoppingCartItem</code> is removed from the <code>ShoppingCart</code>'s <code>items</code> list.
     *
     * @param product  the <code>Product</code> that defines the type of shopping cart item
     * @param quantity the number which the <code>ShoppingCartItem</code> is updated to
     * @see ShoppingCartItem
     */
    public void update(Product product, int quantity) {
        logger.info("update item in cart, product: " + product);
        final Optional<ShoppingCartItem> cartItem = items.stream().filter(scItem -> scItem.getProduct().getId().equals(product.getId())).findFirst();

        if (cartItem.isPresent()) {
            if (quantity > 0) {
                // set item quantity to new value
                cartItem.get().setQuantity(quantity);
            } else {
                // remove from cart
                items.remove(cartItem.get());
            }
        } else {
            items.add(new ShoppingCartItem(product, quantity));
        }
    }

    /**
     * Returns the list of <code>ShoppingCartItems</code>.
     *
     * @return the <code>items</code> list
     * @see ShoppingCartItem
     */
    public List<ShoppingCartItem> getItems() {
        return items;
    }

    /**
     * Returns the sum of quantities for all items maintained in shopping cart <code>items</code> list.
     *
     * @return the number of items in shopping cart
     * @see ShoppingCartItem
     */
    public int getNumberOfItems() {
        return items.stream().mapToInt(scItem -> scItem.getQuantity()).sum();
    }

    /**
     * Returns the sum of the product price multiplied by the quantity for all items in shopping cart list.
     *
     * @return the cost of all items times their quantities
     * @see ShoppingCartItem
     */
    public double getTotal() {
        return items.stream().mapToDouble(scItem -> scItem.getTotal()).sum();
    }

    /**
     * Empties the shopping cart. All items are removed from the shopping cart <code>items</code> list are reset to '<code>0</code>'.
     *
     * @see ShoppingCartItem
     */
    public void clear() {
        logger.info("Clearing shopping cart");
        items.clear();
    }


}
