package com.shawa.data;

import com.shawa.ShawaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<ShawaOrder, Long> {

    @Query("SELECT o FROM ShawaOrder o where o.deliveryCity = 'Siattle'")
    List<ShawaOrder> readOrdersDeliveredInSeattle();
}