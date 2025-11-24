package com.example.fintra;

import com.example.fintra.model.Trade;
import com.example.fintra.model.User;
import com.example.fintra.repository.TradeRepository;
import com.example.fintra.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapper objectMapper;

	private String token;

	@BeforeEach
	void setUp() throws Exception {
		tradeRepository.deleteAll();
		userRepository.deleteAll();

		User user = new User();
		user.setUsername("testuser");
		user.setPassword(passwordEncoder.encode("password"));
		userRepository.save(user);

		MvcResult result = mockMvc.perform(post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\":\"testuser\",\"password\":\"password\"}"))
				.andExpect(status().isOk())
				.andReturn();
		token = result.getResponse().getContentAsString();

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
	void signup_success() throws Exception {
		User newUser = new User();
		newUser.setUsername("newuser");
		newUser.setPassword("password");

		mockMvc.perform(post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("newuser"));
	}

	@Test
	void getTrades_noFilter_returnsAllTrades() throws Exception {
		mockMvc.perform(get("/trades")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	void getTrades_withFromDate_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?from_date=2025-02-01")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("GOOGL"));
	}

	@Test
	void getTrades_withToDate_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?to_date=2025-01-31")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("AAPL"));
	}

	@Test
	void getTrades_withDateRange_returnsFilteredTrades() throws Exception {
		mockMvc.perform(get("/trades?from_date=2025-01-01&to_date=2025-01-31")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stockSymbol").value("AAPL"));
	}
}
