package br.com.fiap.finalchallenge.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.service.StatisticService;
import br.com.fiap.finalchallenge.service.TransactionService;
import br.com.fiap.finalchallenge.util.Util;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Transaction", description = "Transaction Controller REST API")
public class TransactionResource {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private StatisticService statisticService;
	
	Transaction transaction = new Transaction();
	
	@RequestMapping(value= "/saveTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> saveTransaction(@Valid @NotNull @RequestBody TransactionPostJson bodyJson) {
		if(Util.isValid(bodyJson.getTimestamp())) {
			
			transactionService.saveTransaction(bodyJson);
			
			return new ResponseEntity<String>(HttpStatus.valueOf(201));
		}else {
			return new ResponseEntity<String>(HttpStatus.valueOf(204));
		}
		
		
	}
	
}
