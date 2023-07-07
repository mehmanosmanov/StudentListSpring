package com.example.StudentList.controller;

import com.example.StudentList.dto.request.AccountBalanceRequest;
import com.example.StudentList.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
  @PatchMapping("/transaction")
  String cardToCard(@RequestParam Long from, @RequestParam Long to, @RequestParam Double amount) {
    return transactionService.transaction(from, to, amount);
  }

  @PostMapping("/newAccount")
  public void createNewAccount(@RequestBody AccountBalanceRequest request) {
      transactionService.creatBalanceAccount(request);
  }

}
