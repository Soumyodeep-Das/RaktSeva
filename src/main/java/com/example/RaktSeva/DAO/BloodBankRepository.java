package com.example.RaktSeva.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.RaktSeva.Entities.BloodBank;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {

        List<BloodBank> findAll();

        Object findByCity(String city);

        @Query("select u from BloodBank u where u.email = :email")
        BloodBank getUserByUserName(@Param("email") String email);

        @Query("SELECT DISTINCT bb FROM BloodBank bb JOIN bb.blood b WHERE b.bloodGroup = :bloodGroup " +
                        "AND bb.city = :city AND bb.state = :state AND bb.district = :district")
        List<BloodBank> findBloodBanksByBloodGroupAndLocation(
                        @Param("bloodGroup") String bloodGroup,
                        @Param("city") String city,
                        @Param("district") String district,
                        @Param("state") String state);

        @Query("SELECT DISTINCT bb FROM BloodBank bb JOIN bb.blood b WHERE b.bloodGroup = :bloodGroup")
        List<BloodBank> findBloodBanksByBloodGroup(String bloodGroup);

}
