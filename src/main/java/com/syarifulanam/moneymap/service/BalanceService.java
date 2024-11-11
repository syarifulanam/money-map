package com.syarifulanam.moneymap.service;

import com.syarifulanam.moneymap.repository.BalanceRepository;
import com.syarifulanam.moneymap.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    UserService userService;

    public Balance saveBalance(Balance balance) {
        balance.setUser(userService.getLoggedInUser());
        return balanceRepository.save(balance); // "save" untuk simpan data
    }

    public List<Balance> getAllBalance() {
        //return balanceRepository.findAll(); // note : untuk munculin semua data tanpa terkecuali
        return balanceRepository.findByUserId(userService.getLoggedInUser().getId()); // munculin data untuk userId tertentu
    }

    public Balance getBalanceByIdAndUserId(long balanceId) {
        // ketika mencari data WHERE balanceid and userid
        Optional<Balance> existingBalance = balanceRepository.findByIdAndUserId(balanceId, userService.getLoggedInUser().getId());
        if (existingBalance.isPresent()) { // apakah data balance ada atau tidak null ?
            return existingBalance.get(); // return munculin ke postman response
        }
        throw new RuntimeException("Data tidak ditemukan"); // exception throw
    }


    /**
     * Balance existingBalance = getBalanceById(balanceId);
     * BEFORE
     * {
     * "id": 1,
     * "name": "Saldo Gopay",
     * "amount": 374400.00,
     * "notes": null,
     * "user_id": 13,
     * "created_at": "2024-11-07T14:26:01.423+00:00",
     * "updated_at": "2024-11-07T14:26:01.423+00:00"
     * }
     * <p>
     * AFTER
     * {
     * "name" : "Saldo Shopeepay",
     * "amount" : 933500.00,
     * "notes" : "saldo untu belanaja"
     * }
     * <p>
     * existingBalance.setName("Saldo Shopeepay");
     * existingBalance.setAmount(933500.00);
     * existingBalance.setNotes("saldo untu belanaja");
     * <p>
     * balanceRepository.save(existingBalance);
     * {
     * "id": 1,
     * "name": "Saldo Shopeepay",
     * "amount": 933500.00,
     * "notes": "saldo untu belanaja",
     * "user_id": 13,
     * "created_at": "2024-11-07T14:26:01.423+00:00",
     * "updated_at": "2024-11-07T14:30:17.297+00:00"
     * }
     */
    public Balance updateBalance(long balanceId, Balance balance) {
        Balance existingBalance = getBalanceByIdAndUserId(balanceId); // method untuk melakukan pencarian data balance dari id-balance

        existingBalance.setName(balance.getName());
        existingBalance.setAmount(balance.getAmount());
        existingBalance.setNotes(balance.getNotes());

        return balanceRepository.save(existingBalance); // edit / ubah data bisa pake "save"
    }

    public void deleteBalance(long balanceId) {
        Balance existingBalance = getBalanceByIdAndUserId(balanceId);
        balanceRepository.deleteById(existingBalance.getId());
    }
}
