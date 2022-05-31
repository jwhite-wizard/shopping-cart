package com.jacobwhite.ShoppingCart.model;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartItem implements Serializable {

  @NonNull private Long universalProductCode;
  private Double price;
  private boolean isTaxable;
}
