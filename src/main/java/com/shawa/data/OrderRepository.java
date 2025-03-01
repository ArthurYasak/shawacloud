package com.shawa.data;

import com.shawa.ShawaOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<ShawaOrder, UUID> {
}