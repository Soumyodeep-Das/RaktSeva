package com.example.RaktSeva.Config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.RaktSeva.Entities.BloodBank;

public class CustomUserDetails implements UserDetails {

    private BloodBank bank;

    public CustomUserDetails(BloodBank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAuthorities'");

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(bank.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassword'");
        return bank.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getUsername'");
        return bank.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }

}
