package com.example.RaktSeva.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.RaktSeva.Entities.Blood;

@Repository
public interface BloodRepository extends JpaRepository<Blood, Integer> {

    @Query("select b from Blood b where b.bloodBank.id = ?1")
    Page<Blood> findBloodByBloodBank(Integer id, Pageable pageable);

    List<Blood> findByBloodGroupAndBloodBankId(String group, Integer id);

}
