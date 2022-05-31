package com.jacobwhite.ShoppingCart.repository;

import com.jacobwhite.ShoppingCart.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IShoppingCartRepo extends JpaRepository<ShoppingCartEntity, UUID> {}
