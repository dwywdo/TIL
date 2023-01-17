package me.dwywdo.designpattern.structural.adapter;

import me.dwywdo.designpattern.structural.adapter.security.UserDetails;
import me.dwywdo.designpattern.structural.adapter.security.UserDetailsService;

/**
 * Adaptee #2
 */
public class AccountService implements UserDetailsService {
    public Account findAccountByUsername(String username) {
        Account account = new Account();
        account.setName(username);
        account.setPassword(username);
        account.setEmail(username);
        return account;
    }

    public void createNewAccount(Account account) { }
    public void updateAccount(Account account) { }

    @Override
    public UserDetails loadUser(String username) {
        Account accountByUsername = findAccountByUsername(username);
        return accountByUsername;
    }
}
