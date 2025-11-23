package com.example.fintra.repository;

import com.example.fintra.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByBuyDateBetween(LocalDate fromDate, LocalDate toDate);
    List<Trade> findByBuyDateGreaterThanEqual(LocalDate fromDate);
    List<Trade> findByBuyDateLessThanEqual(LocalDate toDate);
}
