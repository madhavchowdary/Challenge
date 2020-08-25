package com.db.awmd.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.db.awmd.challenge.service.TransferService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TransferControllerTest {
	
	 private MockMvc mockMvc;

	  @Test
	  public void transferFromAccountempty() throws Exception {
	    this.mockMvc.perform(post("/v1/transfers").contentType(MediaType.APPLICATION_JSON)
	    .content("{\"accountFrom\": \"\",\"accountTo\": 2345\",\"transferAmount\": 1000\"}")).andExpect(status().isBadRequest());
	  }
	  
	  @Test
	  public void transferToAccountempty() throws Exception {
	    this.mockMvc.perform(post("/v1/transfers").contentType(MediaType.APPLICATION_JSON)
	    .content("{\"accountFrom\": 1234\",\"accountTo\": \"\",\"transferAmount\": 1000\"}")).andExpect(status().isBadRequest());
	  }

	  @Test
	  public void amountTransferNoBody() throws Exception {
	    this.mockMvc.perform(post("/v1/transfers").contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isBadRequest());
	  }

	  @Test
	  public void amountTransferNegativeBalance() throws Exception {
	    this.mockMvc.perform(post("/v1/transfers").contentType(MediaType.APPLICATION_JSON)
	    		.content("{\"accountFrom\": 1234\",\"accountTo\": 2345\",\"transferAmount\": -1000\"}")).andExpect(status().isBadRequest());
	  }
}