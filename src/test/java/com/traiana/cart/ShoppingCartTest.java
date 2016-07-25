package com.traiana.cart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yoava on 25/07/2016.
 */
public class ShoppingCartTest {

    ShoppingCart shoppingCart;

    @Before
    public void setUp() throws Exception {
        shoppingCart = new ShoppingCart();
    }

    @After
    public void tearDown() throws Exception {
        shoppingCart.clear();
    }

    @Test
    public void testIsExists() throws Exception {
        final Product product = new Product(1, "Item 1", BigDecimal.valueOf(10L));
        shoppingCart.addItem(product);
        assertTrue(shoppingCart.isExist(product));
    }

    @Test
    public void testAddItem() throws Exception {
        final Product product = new Product(1, "Item 1", BigDecimal.valueOf(10L));
        shoppingCart.addItem(product);

        assertTrue(shoppingCart.isExist(product));
    }

    @Test
    public void testUpdate() throws Exception {
        final Product product = new Product(1, "Item 1", BigDecimal.valueOf(10L));
        shoppingCart.update(product, 3);

        final Optional<ShoppingCartItem> itemByProduct = shoppingCart.getItemByProduct(product);
        assertTrue(itemByProduct.isPresent());
        assertEquals(3, itemByProduct.get().getQuantity());
    }

    @Test
    public void testUpdateRemove() throws Exception {
        final Product product = new Product(1, "Item 1", BigDecimal.valueOf(10L));
        shoppingCart.update(product, 3);

        final Optional<ShoppingCartItem> itemByProduct = shoppingCart.getItemByProduct(product);
        assertTrue(itemByProduct.isPresent());
        assertEquals(3, itemByProduct.get().getQuantity());

        shoppingCart.update(product, 0);
        assertFalse(shoppingCart.isExist(product));
    }

    @Test
    public void testGetNumberOfItems() throws Exception {
        shoppingCart.update(new Product(1, "Item 1", BigDecimal.valueOf(10L)), 1);
        shoppingCart.update(new Product(2, "Item 2", BigDecimal.valueOf(15L)), 2);
        shoppingCart.update(new Product(3, "Item 3", BigDecimal.valueOf(20L)), 3);

        assertEquals(6, shoppingCart.getNumberOfItems());
    }

    @Test
    public void testGetTotal() throws Exception {
        shoppingCart.update(new Product(1, "Item 1", BigDecimal.valueOf(10L)), 1);
        shoppingCart.update(new Product(2, "Item 2", BigDecimal.valueOf(15L)), 2);
        shoppingCart.update(new Product(3, "Item 3", BigDecimal.valueOf(20L)), 3);

        double expectedTotal = (10 + (15 * 2) + (20 * 3));
        assertEquals(Double.valueOf(expectedTotal), Double.valueOf(shoppingCart.getTotal()));
    }

    @Test
    public void testClear() throws Exception {
        shoppingCart.update(new Product(1, "Item 1", BigDecimal.valueOf(10L)), 1);
        shoppingCart.update(new Product(2, "Item 2", BigDecimal.valueOf(15L)), 2);
        shoppingCart.update(new Product(3, "Item 3", BigDecimal.valueOf(20L)), 3);
        shoppingCart.clear();
        assertEquals(0, shoppingCart.getNumberOfItems());
    }
}