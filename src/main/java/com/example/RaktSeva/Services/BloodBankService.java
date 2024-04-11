package com.example.RaktSeva.Services;

import java.util.List;

import com.example.RaktSeva.Entities.BloodBank;

public interface BloodBankService {
    BloodBank addBloodBank(BloodBank bloodBank);

    BloodBank updateBloodBank(BloodBank bloodBank, Integer bank_id);

    void deleteBloodBank(Integer bank_id);

    BloodBank getBloodBankById(Integer bank_id);

    List<BloodBank> getAllBloodBanks();

    Object searchBloodBanksByCity(String city);
}
