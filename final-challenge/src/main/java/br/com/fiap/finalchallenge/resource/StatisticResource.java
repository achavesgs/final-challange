package br.com.fiap.finalchallenge.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finalchallenge.model.Statistic;

@RestController
public class StatisticResource {
	
	@RequestMapping(value = "/listStatistics", method = RequestMethod.GET)
	public ResponseEntity<Statistic> listStatistic() {
		return null;
	}

}
