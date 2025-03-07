package com.ivrs.service.impl;

import com.ivrs.DTO.RequestDTO;
import com.ivrs.DTO.SalaryDetailsDTO;
import com.ivrs.entities.Earning;
import com.ivrs.entities.Employee;
import com.ivrs.entities.PaySummary;
import com.ivrs.entities.UserAuth;
import com.ivrs.globalException.CustomerNotFoundException;
import com.ivrs.repositories.EarningRepository;
import com.ivrs.repositories.EmployeeRepository;
import com.ivrs.repositories.PaySummeryRepository;
import com.ivrs.repositories.UserAuthRepository;
import com.ivrs.service.IvrsService;
import jdk.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IvrsServiceImpl implements IvrsService {

    private static final Logger logger = LoggerFactory.getLogger(IvrsServiceImpl.class);

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    PaySummeryRepository paySummeryRepository;

    @Autowired
    EarningRepository earningRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Object getUserDetails(RequestDTO requestDTO) {
        logger.info("Request received for customer number: {}", requestDTO.getCustomerNumber()); // Log the request

        Optional<UserAuth> userAuth = this.userAuthRepository.findByMobileNumber(requestDTO.getCustomerNumber());

        if (userAuth.isPresent()) {
            logger.info("Customer found for mobile number: {}", requestDTO.getCustomerNumber()); // Log when customer is found
            return userAuth;
        } else {
            logger.warn("Customer not found for mobile number: {}", requestDTO.getCustomerNumber()); // Log when no customer is found
            throw new CustomerNotFoundException("Customer not found for mobile number: " + requestDTO.getCustomerNumber()); // Throw custom exception
        }
    }

    private Object getSalaryDetails(RequestDTO requestDTO) {
        UserAuth userAuth = (UserAuth) getUserDetails(requestDTO);
        String cdacNo = userAuth.getAccountNumber();
        SalaryDetailsDTO salaryDetailsDTO = new SalaryDetailsDTO();

        // Fetch PaySummary where status is "C"
        List<PaySummary> paySummary = paySummeryRepository.findByCdaoNoAndRecordStatus(cdacNo, "C");
        if (!paySummary.isEmpty() && paySummary.get(0) != null) {
            salaryDetailsDTO.setMonth(paySummary.get(0).getMonthEnding());
            salaryDetailsDTO.setRemittance(String.valueOf(paySummary.get(0).getEcsAmount()));
        }

        // Fetch latest earnings
        getLatestEarning(cdacNo, 1).ifPresent(earning -> salaryDetailsDTO.setBasic(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 3).ifPresent(earning -> salaryDetailsDTO.setDA(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 4).ifPresent(earning -> salaryDetailsDTO.setMSP(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 6).ifPresent(earning -> salaryDetailsDTO.setNPA(earning.getAmount().toString()));

        return salaryDetailsDTO;
    }


    public Optional<Earning> getLatestEarning(String cdacNo, Integer fkPayCode) {
        Optional<Employee> employee = employeeRepository.findByCdaoNo(cdacNo);
        if (employee.isPresent()) {
            List<Earning> earnings = earningRepository.findByFkEmployeeAndFkPayCode(employee.get().getId(), fkPayCode);

            return earnings.stream()
                    .max(Comparator.comparing(Earning::getId));
        }
        return Optional.empty();
    }


}



