package br.com.fiap.finalchallenge.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.service.StatisticService;

@RestController
public class StatisticResource {

	@Autowired
	StatisticService statisticService;

	@RequestMapping(value = "/getStatistic", method = RequestMethod.GET)
	public ResponseEntity<Statistic> getStatistic() {
		Statistic statistic = this.statisticService.getStatistic();

		ResponseEntity<Statistic> response = new ResponseEntity<Statistic>(statistic, HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/getAllStatistics", method = RequestMethod.GET)
	public ResponseEntity<List<Statistic>> getAllStatistics() {
		
		List<Statistic> statistics = this.statisticService.getAllStatistics();
		
		ResponseEntity<List<Statistic>> response = new ResponseEntity<List<Statistic>>(statistics, HttpStatus.OK);
		
		return response;
	}

}
