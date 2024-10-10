package com.thorabh.ecommerce.repository;

import com.thorabh.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
