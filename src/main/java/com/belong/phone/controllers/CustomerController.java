package com.belong.phone.controllers;

import com.belong.phone.common.HttpHeader;
import com.belong.phone.models.CustomerDto;
import com.belong.phone.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.Callable;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("v1/customers/{customerId}/phones")
  public Callable<ResponseEntity<CustomerDto>> getPhoneNumbers(
      @RequestHeader final HttpHeaders requestHeaders,
      @PathVariable(name = "customerId") @NotBlank String customerId) {
    return () -> new ResponseEntity<>(customerService.getPhoneNumberForCustomer(requestHeaders, customerId), HttpStatus.OK);
  }

  @PutMapping("v1/customers/{customerId}/activate")
  public Callable<ResponseEntity<CustomerDto>> activatePhoneNumber(
      @RequestHeader final HttpHeaders requestHeaders,
      @PathVariable(value = "customerId") @NotBlank String customerId,
      @RequestBody @Valid CustomerDto customer
  ) {
    String correlationId = requestHeaders.getFirst(HttpHeader.X_CORRELATION_ID);
    return () ->new ResponseEntity(customerService.activatePhoneNumber(requestHeaders, customerId, customer), HttpStatus.ACCEPTED);
  }
}

