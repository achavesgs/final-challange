package br.com.fiap.finalchallenge.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.finalchallenge.FinalChallengeApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FinalChallengeApplication.class)
@RunWith(SpringRunner.class)
public class StatisticResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getStatistics() throws Exception {
		 this.mockMvc.perform(get("/getStatistic").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
         .andExpect(status().isOk())
         .andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void getStatisticsAll() throws Exception {
		 this.mockMvc.perform(get("/getAllStatistics").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
         .andExpect(status().isOk())
         .andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
