package com.ivrs.controller;

import com.ivrs.DTO.DOIIRequestDTO;
import com.ivrs.DAO.PcdaoDao;
import com.ivrs.DTO.RequestDTO;
import com.ivrs.DTO.SessionRequestDTO;
import com.ivrs.config.SessionManager;
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
    private final SessionManager sessionManager;
    private final PcdaoDao pcdaoDao;
    public IvrsController(IvrsService ivrsService, SessionManager sessionManager, PcdaoDao pcdaoDao) {
        this.ivrsService = ivrsService;
        this.sessionManager = sessionManager;
        this.pcdaoDao = pcdaoDao;
    }


    @PostMapping("/activate-session")
    public ResponseEntity<?> activateSession(@RequestBody SessionRequestDTO request) {
        boolean isValidUser = pcdaoDao.validateUser(request.getMobileNumber(), request.getCode());
        if (isValidUser) {
            sessionManager.activateSession(request.getMobileNumber());
            return ResponseEntity.ok("Session activated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
    }

    @PostMapping("/getDetails")
    public ResponseEntity<Object> getCustomerDetails(@RequestBody RequestDTO requestDTO){
        if (!sessionManager.isSessionActive(requestDTO.getCustomerNumber())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }
        Object response = null;
        try{
           response = ivrsService.getCustomerDetails(requestDTO);
      }catch (Exception e){
          logger.error("Exception while retrieving data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
      }
      return ResponseEntity.status(HttpStatus.OK).body(response);
  }

    @PostMapping("/getDOIIDetails")
    public ResponseEntity<Object> getDOIIDetails(@RequestBody DOIIRequestDTO requestDTO){
        Object response = null;
        try{
            response = ivrsService.getCustomerDetails(requestDTO);
        }catch (Exception e){
            logger.error("Exception while retrieving data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/deactivate-session/{mobileNumber}")
    public ResponseEntity<?> deActiveateSession(@PathVariable String mobileNumber) {
        if (mobileNumber != null) {
            sessionManager.removeSession(mobileNumber);
            return ResponseEntity.ok("Session Deacruvated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid sessions..");
        }
    }
}
