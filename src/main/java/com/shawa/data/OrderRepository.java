package com.shawa.data;

import com.shawa.ShawaOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<ShawaOrder, String> {
}