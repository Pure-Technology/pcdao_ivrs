package com.ivrs.controller;

import com.ivrs.DTO.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/getDetails")
public class IvrsController {


  public ResponseEntity<Object> getCustomerDetails(@RequestBody RequestDTO requestDTO){
      return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
