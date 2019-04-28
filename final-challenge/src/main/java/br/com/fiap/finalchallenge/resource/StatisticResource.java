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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Statistic", description = "Statistic Controller REST API")
public class StatisticResource {

	@Autowired
	StatisticService statisticService;

	@ApiOperation(httpMethod = "GET", value = "Get Transactions Statistic")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns Statistic and Success Status", response = Statistic.class)
    })
	@RequestMapping(value = "/getStatistic", method = RequestMethod.GET)
	public ResponseEntity<Statistic> getStatistic() {
		Statistic statistic = this.statisticService.getStatistic();

		ResponseEntity<Statistic> response = new ResponseEntity<Statistic>(statistic, HttpStatus.valueOf(201));

		return response;
	}

	@ApiOperation(httpMethod = "GET", value = "Get All Transactions Statistics")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns Statistics List and Success Status", response = Statistic.class)
    })
	@RequestMapping(value = "/getAllStatistics", method = RequestMethod.GET)
	public ResponseEntity<List<Statistic>> getAllStatistics() {
		
		List<Statistic> statistics = this.statisticService.getAllStatistics();
		
		ResponseEntity<List<Statistic>> response = new ResponseEntity<List<Statistic>>(statistics, HttpStatus.valueOf(201));
		
		return response;
	}

}
