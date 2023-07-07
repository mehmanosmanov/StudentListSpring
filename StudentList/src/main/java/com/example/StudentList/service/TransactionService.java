package com.example.StudentList.service;

import com.example.StudentList.dto.request.AccountBalanceRequest;
import com.example.StudentList.entity.AccountBalance;
import com.example.StudentList.repository.AccountBalanceRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This part of codes belongs to the money transaction
 **/
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountBalanceRepository accountBalanceRepository;

    @Transactional
    public String transaction(Long from, Long to, Double amount) {
        accountBalanceRepository.subtractAccountBalance(from, amount);
        accountBalanceRepository.addAccountBalance(to, amount);
        return "Transaction successful";
    }

    public void creatBalanceAccount(AccountBalanceRequest request) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setName(request.getName());
        accountBalance.setSurName(request.getSurName());
        accountBalance.setAccountNum(request.getAccountNum());
        accountBalanceRepository.save(accountBalance);
    }
}
