package com.ivrs.controller;

import com.ivrs.DTO.RequestDTO;
import com.ivrs.service.IvrsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api")
public class IvrsController {

    private static final Logger logger = LoggerFactory.getLogger(IvrsController.class);

    private final IvrsService ivrsService;

    public IvrsController(IvrsService ivrsService) {
        this.ivrsService = ivrsService;
    }

    @PostMapping("/getDetails")
    public ResponseEntity<Object> getCustomerDetails(@RequestBody RequestDTO requestDTO){
        Object response = null;
        try{
           response = ivrsService.getCustomerDetails(requestDTO);
      }catch (Exception e){
          logger.error("Exception while retrieving data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
      }
      return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
