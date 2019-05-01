package br.com.fiap.finalchallenge.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.finalchallenge.FinalChallengeApplication;
import br.com.fiap.finalchallenge.service.StatisticService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FinalChallengeApplication.class)
@RunWith(SpringRunner.class)
public class StatisticResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getStatistics() throws Exception {
		 this.mvc.perform(get("/getStatistic").accept(MediaType.APPLICATION_JSON))
         .andExpect(status().is(201));
	}

}
