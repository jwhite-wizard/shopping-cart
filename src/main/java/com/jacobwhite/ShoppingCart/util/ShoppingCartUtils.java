package com.jacobwhite.ShoppingCart.util;

import com.jacobwhite.ShoppingCart.entity.ShoppingCartEntity;
import com.jacobwhite.ShoppingCart.model.ShoppingCartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ShoppingCartUtils {
  public static final Double TAX_RATE = 0.0825;

  protected void calculateTaxTotal(ShoppingCartEntity shoppingCart) {
    Double tax = shoppingCart.getTaxTotal() == null ? 0.00 : shoppingCart.getTaxTotal();
    for(ShoppingCartItem item : shoppingCart.getItems()){
      tax += item.isTaxable() ? item.getPrice() * TAX_RATE : 0.00;
    }
    shoppingCart.setTaxTotal(tax.doubleValue());
  }

  protected void calculateSubtotal(ShoppingCartEntity shoppingCart) {
    Double subTotal = shoppingCart.getSubtotal() == null ? 0.00 : shoppingCart.getSubtotal();
    for (ShoppingCartItem item : shoppingCart.getItems()) {
      subTotal += item.getPrice();
    }
    shoppingCart.setSubtotal(subTotal);
  }

  public void calculateGrandTotal(ShoppingCartEntity shoppingCart) {
    if(shoppingCart.getTaxTotal() == null){
      calculateTaxTotal(shoppingCart);
    } if(shoppingCart.getSubtotal() == null){
      calculateSubtotal(shoppingCart);
    }
    shoppingCart.setGrandTotal(shoppingCart.getTaxTotal()+shoppingCart.getSubtotal());
  }
}
