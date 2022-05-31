package com.jacobwhite.ShoppingCart.controller;

import com.jacobwhite.ShoppingCart.entity.ShoppingCartEntity;
import com.jacobwhite.ShoppingCart.repository.IShoppingCartRepo;
import com.jacobwhite.ShoppingCart.util.ShoppingCartUtils;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartController {
  @Autowired IShoppingCartRepo shoppingCartRepo;
  @Autowired ShoppingCartUtils shoppingCartUtils;

  @PostMapping("/shopping-carts")
  public ResponseEntity<ShoppingCartEntity> createShoppingCart(
      @RequestBody ShoppingCartEntity shoppingCart) {
    shoppingCartUtils.calculateGrandTotal(shoppingCart);
    try {
      return new ResponseEntity<>(shoppingCartRepo.save(shoppingCart), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
