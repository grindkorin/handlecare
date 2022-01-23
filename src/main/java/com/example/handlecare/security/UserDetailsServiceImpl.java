package com.example.handlecare.security;


import com.example.handlecare.entity.User;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    DeliverServiceImpl deliverService;
    @Autowired
    RecipientServiceImpl recipientService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = deliverService.findByLogin(username);
        System.err.println(user);
        System.out.println("DELIVER");
        if(user == null){
            user = recipientService.findByLogin(username);
            System.err.println(user);
            System.out.println("RECIPIENT");
            if(user == null)
                throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserDetailsImpl(user);
    }

}
