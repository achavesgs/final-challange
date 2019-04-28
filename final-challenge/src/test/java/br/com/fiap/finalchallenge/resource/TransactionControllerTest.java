package br.com.fiap.finalchallenge.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.finalchallenge.FinalChallengeApplication;
import br.com.fiap.finalchallenge.util.Util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;


@WebMvcTest(FinalChallengeApplication.class)
@RunWith(SpringRunner.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void postTransaction() throws Exception {
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"amount\": 5.0");
		json.append(",");
		json.append("\"timestamp\": " + String.valueOf(Util.converToTimeStamp(LocalDateTime.now())));
		json.append("}");
		
		 this.mockMvc.perform(post("/transactions")
		.contentType("application/json;charset=UTF-8")
		.content(json.toString())
		.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(status().isCreated())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
}
