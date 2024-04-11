package com.example.RaktSeva.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.RaktSeva.DAO.BloodBankRepository;
import com.example.RaktSeva.Entities.BloodBank;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'loadUserByUsername'");
        // fetching user data
        BloodBank bank = bloodBankRepository.getUserByUserName(username);

        if (bank == null)
            throw new UsernameNotFoundException("Could not found the user");

        CustomUserDetails customUserDetails = new CustomUserDetails(bank);

        return customUserDetails;
    }

}
