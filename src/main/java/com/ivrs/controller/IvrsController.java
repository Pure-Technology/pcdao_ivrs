package com.ivrs.controller;

import com.ivrs.DTO.RequestDTO;
import com.ivrs.service.IvrsService;
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

    private final IvrsService ivrsService;

    public IvrsController(IvrsService ivrsService) {
        this.ivrsService = ivrsService;
    }

    public ResponseEntity<Object> getCustomerDetails(@RequestBody RequestDTO requestDTO){
      try{
          Object response = ivrsService.getCustomerDetails(requestDTO);
      }catch (Exception e){
          log.error("Exception while retrieving data");
      }
      return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
