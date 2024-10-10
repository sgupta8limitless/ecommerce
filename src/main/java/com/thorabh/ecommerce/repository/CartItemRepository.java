package com.thorabh.ecommerce.repository;

import com.thorabh.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    public List<CartItem> findByUserId(Long userid);

}

