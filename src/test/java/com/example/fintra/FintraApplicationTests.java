package com.example.fintra;

import com.example.fintra.model.Trade;
import com.example.fintra.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class FintraApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TradeRepository tradeRepository;

	@BeforeEach
	void setUp() {
		tradeRepository.deleteAll();

		Trade trade1 = new Trade();
		trade1.setStockSymbol("AAPL");
		trade1.setBuyPrice(new BigDecimal("150.00"));
		trade1.setBuyDate(LocalDate.of(2025, 1, 15));
		trade1.setQuantity(10);
		tradeRepository.save(trade1);

		Trade trade2 = new Trade();
		trade2.setStockSymbol("GOOGL");
		trade2.setBuyPrice(new BigDecimal("2800.00"));
		trade2.setBuyDate(LocalDate.of(2025, 2, 10));
		trade2.setQuantity(5);
		tradeRepository.save(trade2);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getTrades_noFilter_returnsAllTrades() throws Exception {
		mockMvc.perform(get("/trades"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void getTrades_withFromDate_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?from_date=2025-02-01"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("GOOGL"));
	}

	@Test
	void getTrades_withToDate_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?to_date=2025-01-31"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("AAPL"));
	}

	@Test
	void getTrades_withDateRange_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?from_date=2025-01-01&to_date=2025-01-31"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("AAPL"));
	}
}
