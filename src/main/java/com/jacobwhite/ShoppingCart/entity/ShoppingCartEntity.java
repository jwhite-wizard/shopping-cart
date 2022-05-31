package com.jacobwhite.ShoppingCart.entity;

import com.jacobwhite.ShoppingCart.model.ShoppingCartItem;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "shopping_carts_table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartEntity {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Nullable private Double subtotal;
  @Nullable private Double taxTotal;
  @Nullable private Double grandTotal;

  @NonNull
  @Column(length = 8192)
  private ShoppingCartItem[] items;

  @Nullable private String[] couponCodes;
}
