package com.example.RaktSeva.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RaktSeva.DAO.BloodBankRepository;
import com.example.RaktSeva.Entities.BloodBank;
import com.example.RaktSeva.Services.BloodBankService;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public BloodBank addBloodBank(BloodBank bloodBank) {
        return this.bloodBankRepository.save(bloodBank);
    }

    @Override
    public BloodBank updateBloodBank(BloodBank bloodBank, Integer bank_id) {
        BloodBank bank = this.bloodBankRepository.findById(bank_id)
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));
        bank.setName(bloodBank.getName());
        bank.setAddress(bloodBank.getAddress());
        bank.setContactNumber(bloodBank.getContactNumber());
        bank.setEmail(bloodBank.getEmail());
        bank.setLicense(bloodBank.getLicense());
        bank.setCity(bloodBank.getCity());
        bank.setDistrict(bloodBank.getDistrict());
        bank.setState(bloodBank.getState());
        bank.setCountry(bloodBank.getCountry());
        return this.bloodBankRepository.save(bank);
    }

    @Override
    public void deleteBloodBank(Integer bank_id) {
        BloodBank bank = this.bloodBankRepository.findById(bank_id)
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));
        this.bloodBankRepository.delete(bank);
    }

    @Override
    public BloodBank getBloodBankById(Integer bank_id) {
        BloodBank bank = this.bloodBankRepository.findById(bank_id)
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));
        return bank;
    }

    @Override
    public List<BloodBank> getAllBloodBanks() {

        return this.bloodBankRepository.findAll();
    }

    @Override
    public Object searchBloodBanksByCity(String city) {
        return this.bloodBankRepository.findByCity(city);
    }

}
