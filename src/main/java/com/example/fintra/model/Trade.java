package com.example.fintra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_transaction")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String stockSymbol;

    @NotNull
    @Positive
    private BigDecimal buyPrice;

    @NotNull
    private LocalDate buyDate;

    @NotNull
    @Positive
    private Integer quantity;

    private BigDecimal totalBuyValue;
    private LocalDate sellDate;
    private BigDecimal sellPrice;
    private BigDecimal totalSellValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
        calculateTotalBuyValue();
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateTotalBuyValue();
        calculateTotalSellValue();
    }

    public BigDecimal getTotalBuyValue() {
        return totalBuyValue;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
        calculateTotalSellValue();
    }

    public BigDecimal getTotalSellValue() {
        return totalSellValue;
    }

    private void calculateTotalBuyValue() {
        if (buyPrice != null && quantity != null) {
            this.totalBuyValue = buyPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    private void calculateTotalSellValue() {
        if (sellPrice != null && quantity != null) {
            this.totalSellValue = sellPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}