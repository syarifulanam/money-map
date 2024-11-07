package com.syarifulanam.spring.boot.moneyMap.service;

import com.syarifulanam.spring.boot.moneyMap.entity.Balance;
import com.syarifulanam.spring.boot.moneyMap.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Balance saveBalance(Balance balance) {
        return balanceRepository.save(balance); // "save" untuk simpan data
    }

    public List<Balance> getAllBalance() {
        return balanceRepository.findAll();
    }

    public Balance getBalanceById(long balanceId) {
        // method untuk melakukan pencarian data balance dari id-balance
        Optional<Balance> existingBalance = balanceRepository.findById(balanceId); // ketika mencari data WHERE id
        if (existingBalance.isPresent()) { // apakah data balance ada atau tidak null ?
            return existingBalance.get();
        }
        throw new RuntimeException("Data tidak ditemukan"); // exception throw
    }

    public Balance updateBalance(long balanceId, Balance balance) {
        Balance existingBalance = getBalanceById(balanceId); // method untuk melakukan pencarian data balance dari id-balance

        existingBalance.setName(balance.getName());
        existingBalance.setAmount(balance.getAmount());
        existingBalance.setNotes(balance.getNotes());

        return balanceRepository.save(existingBalance); // edit / ubah data bisa pake "save"
    }

    public void deleteBalance(long balanceId) {
        Balance existingBalance = getBalanceById(balanceId);
        balanceRepository.deleteById(existingBalance.getId());
    }
}
