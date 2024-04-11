package com.example.RaktSeva.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RaktSeva.DAO.BloodRepository;
import com.example.RaktSeva.Entities.Blood;
import com.example.RaktSeva.Services.BloodService;

@Service
public class BloodServiceImpl implements BloodService {

    @Autowired
    private BloodRepository bloodRepository;

    public BloodServiceImpl(BloodRepository bloodRepository) {
        this.bloodRepository = bloodRepository;
    }

    @Override
    public Blood addBlood(Blood blood) {
        return this.bloodRepository.save(blood);
    }

    @Override
    public Blood updateBlood(Blood updated_blood, Integer blood_id) {
        Blood blood = this.bloodRepository.findById(blood_id)
                .orElseThrow(() -> new RuntimeException("Blood not found"));
        blood.setBloodBank(updated_blood.getBloodBank());
        blood.setBloodAmount(updated_blood.getBloodAmount());
        blood.setBloodExpireDate(updated_blood.getBloodExpireDate());
        blood.setBloodExpireDate(updated_blood.getBloodExpireDate());
        blood.setBloodBank(updated_blood.getBloodBank());

        return this.bloodRepository.save(blood);
    }

    @Override
    public void deleteBlood(Integer blood_id) {
        Blood blood = this.bloodRepository.findById(blood_id)
                .orElseThrow(() -> new RuntimeException("Blood not found"));
        this.bloodRepository.delete(blood);
    }

    @Override
    public Blood getBloodById(Integer blood_id) {
        Blood blood = this.bloodRepository.findById(blood_id)
                .orElseThrow(() -> new RuntimeException("Blood not found"));
        return blood;
    }

    @Override
    public List<Blood> getAllBloods() {
        return this.bloodRepository.findAll();
    }

}
