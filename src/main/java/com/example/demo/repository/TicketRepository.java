package com.example.demo.repository;

import com.example.demo.entity.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM orders_tickets WHERE orders_order_id = :orderId", nativeQuery = true)
    void deleteJoinTableEntries(@Param("orderId") int orderId);
}
