package com.example.StudentList.repository;

import com.example.StudentList.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
  @Modifying
  @Query(value = "update account_balance ab set balance =(select ab.balance where ab.account_num=:to) + :amount where account_num =:to",
      nativeQuery = true)
  void addAccountBalance(Long to, Double amount);

  @Modifying
  @Query(value = "update account_balance ab set balance =(select ab.balance where ab.account_num=:from) - :amount where account_num =:from",
          nativeQuery = true)
  void subtractAccountBalance(Long from, Double amount);


}
