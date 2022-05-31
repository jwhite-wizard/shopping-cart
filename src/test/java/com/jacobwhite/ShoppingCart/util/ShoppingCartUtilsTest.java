package com.jacobwhite.ShoppingCart.util;

import com.jacobwhite.ShoppingCart.entity.ShoppingCartEntity;
import com.jacobwhite.ShoppingCart.model.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShoppingCartUtilsTest {
  ShoppingCartItem taxableItem;
  ShoppingCartItem untaxableItem;
  ShoppingCartEntity shoppingCart;
  ShoppingCartUtils utils;

  @BeforeEach
  public void setup(){
    taxableItem =
            ShoppingCartItem.builder().isTaxable(true).price(1.00).universalProductCode(123L).build();
    untaxableItem =
            ShoppingCartItem.builder().isTaxable(false).price(1.00).universalProductCode(456L).build();

    shoppingCart =
            ShoppingCartEntity.builder()
                    .items(new ShoppingCartItem[] {taxableItem, untaxableItem})
                    .build();
    utils = new ShoppingCartUtils();
  }

  @Test
  public void testCalculateTaxTotal_calculates() {
    Double expected = 0.0825;

    utils.calculateTaxTotal(shoppingCart);
    assertEquals(expected, shoppingCart.getTaxTotal());
  }

  @Test
  public void testCalculateSubtotal_calculates(){
    Double expected = 2.0;

    utils.calculateSubtotal(shoppingCart);
    assertEquals(expected, shoppingCart.getSubtotal());
  }

  @Test
  public void testCalculateGrandTotal_whenTaxTotalAndSubTotalNull_calculates(){
    Double expected = 2.0825;

    utils.calculateGrandTotal(shoppingCart);
    assertEquals(expected, shoppingCart.getGrandTotal());
  }
}
