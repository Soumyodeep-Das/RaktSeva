package com.example.RaktSeva.Services;

import java.util.List;

import com.example.RaktSeva.Entities.Blood;

public interface BloodService {
    Blood addBlood(Blood blood);

    Blood updateBlood(Blood blood, Integer blood_id);

    void deleteBlood(Integer blood_id);

    Blood getBloodById(Integer blood_id);

    List<Blood> getAllBloods();
}
