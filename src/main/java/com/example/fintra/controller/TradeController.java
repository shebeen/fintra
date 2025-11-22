package com.example.fintra.controller;

import com.example.fintra.model.Trade;
import com.example.fintra.repository.TradeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @PostMapping
    public Trade addTrade(@Valid @RequestBody Trade trade) {
        return tradeRepository.save(trade);
    }

    @GetMapping
    public List<Trade> getTrades() {
        return tradeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return tradeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @Valid @RequestBody Trade tradeDetails) {
        return tradeRepository.findById(id)
                .map(trade -> {
                    trade.setStockSymbol(tradeDetails.getStockSymbol());
                    trade.setBuyPrice(tradeDetails.getBuyPrice());
                    trade.setBuyDate(tradeDetails.getBuyDate());
                    trade.setQuantity(tradeDetails.getQuantity());
                    trade.setSellDate(tradeDetails.getSellDate());
                    trade.setSellPrice(tradeDetails.getSellPrice());
                    return ResponseEntity.ok(tradeRepository.save(trade));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        return tradeRepository.findById(id)
                .map(trade -> {
                    tradeRepository.delete(trade);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}