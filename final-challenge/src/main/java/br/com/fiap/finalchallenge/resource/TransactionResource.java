package br.com.fiap.finalchallenge.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.finalchallenge.exception.TransactionExpiredException;
import br.com.fiap.finalchallenge.exception.TransactionOutOfFutureWindow;
import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.service.StatisticService;
import br.com.fiap.finalchallenge.service.impl.TransactionServiceImpl;
import br.com.fiap.finalchallenge.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(value = "Transaction", description = "Transaction Controller REST API")
public class TransactionResource {

	@Autowired
	private TransactionServiceImpl transactionService;

	@Autowired
	private StatisticService statisticService;

	Transaction transaction = new Transaction();

	@ApiOperation(httpMethod = "POST", value = "Save Transaction")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns generated Transaction and Created Status", response = String.class)
    })
	@RequestMapping(value = "/saveTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> saveTransaction(@Valid @NotNull @RequestBody TransactionPostJson bodyJson) {

		try {
			transactionService.saveTransaction(bodyJson);

			this.statisticService.add(transactionService.getCurrentTransaction());

		} catch (TransactionExpiredException | TransactionOutOfFutureWindow e) {
			return new ResponseEntity<String>(HttpStatus.valueOf(204));
		}

		return new ResponseEntity<String>(HttpStatus.valueOf(201));

	}

}
