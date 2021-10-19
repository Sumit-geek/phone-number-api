package com.belong.phone.controllers;

import com.belong.phone.models.PhoneDto;
import com.belong.phone.services.PhoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PhoneController {

  private final PhoneService phoneService;

  @GetMapping("v1/phones")
  public Callable<ResponseEntity<List<PhoneDto>>> getPhoneNumbers(
      @RequestHeader final HttpHeaders requestHeaders) {
    return () -> new ResponseEntity<>(phoneService.getPhoneNumbers(requestHeaders), HttpStatus.OK);
  }

}

