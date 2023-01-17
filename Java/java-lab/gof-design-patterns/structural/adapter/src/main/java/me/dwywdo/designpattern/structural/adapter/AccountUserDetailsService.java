package me.dwywdo.designpattern.structural.adapter;

import me.dwywdo.designpattern.structural.adapter.security.UserDetails;
import me.dwywdo.designpattern.structural.adapter.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {

    AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        Account accountByUsername = accountService.findAccountByUsername(username);
        return new AccountUserDetails(accountByUsername);
    }
}
