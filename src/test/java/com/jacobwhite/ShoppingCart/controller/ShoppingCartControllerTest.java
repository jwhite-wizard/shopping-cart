package com.jacobwhite.ShoppingCart.controller;

import com.jacobwhite.ShoppingCart.entity.ShoppingCartEntity;
import com.jacobwhite.ShoppingCart.model.ShoppingCartItem;
import com.jacobwhite.ShoppingCart.repository.IShoppingCartRepo;

import com.jacobwhite.ShoppingCart.util.ShoppingCartUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ShoppingCartControllerTest {
    @Mock
    IShoppingCartRepo shoppingCartRepo;
    @Mock
    ShoppingCartUtils shoppingCartUtils;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateShoppingCart_createsShoppingCart() {
        ShoppingCartController controller = new ShoppingCartController(shoppingCartRepo, shoppingCartUtils);
        ShoppingCartItem item1 = ShoppingCartItem.builder().isTaxable(true).price(3.99).universalProductCode(123456789012L).build();
        ShoppingCartItem item2 = ShoppingCartItem.builder().isTaxable(false).price(4.01).universalProductCode(987654321098L).build();
        ShoppingCartItem[] items = {item1, item2};
        ShoppingCartEntity requestCart = ShoppingCartEntity.builder().items(items).build();
        ShoppingCartEntity expectedCart = requestCart;
        expectedCart.setId(UUID.randomUUID());
        when(shoppingCartRepo.save(requestCart)).thenReturn(expectedCart);
        ResponseEntity<ShoppingCartEntity> actual = controller.createShoppingCart(requestCart);
        System.out.println(actual);
        assertEquals(items, actual.getBody().getItems());
    }

//    TODO: test case for exception
}
