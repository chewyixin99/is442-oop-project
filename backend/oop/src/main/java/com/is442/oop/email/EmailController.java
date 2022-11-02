package com.is442.oop.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.is442.oop.data.models.Loan;
import com.is442.oop.data.payloads.response.DataResponse;
import com.is442.oop.loan.LoanService;

/**
 * EmailController to test EmailService independently
 */
@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    LoanService loanService;

    @Autowired
    EmailService emailService;

    @GetMapping("/sendLoanConfirmationEmail/{loanId}")
    public ResponseEntity<DataResponse> sendLoanConfirmationEmail(@PathVariable("loanId") Integer loanId) {
        try {
            Loan loan = loanService.getLoanByLoanID(loanId);
            int templateId = loan.getPass().getIsPhysical() ? 4 : 3;
            emailService.sendLoanConfirmationEmail(loan, templateId);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataResponse(null, e), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new DataResponse(null, "Email"), HttpStatus.OK);
    }
}
