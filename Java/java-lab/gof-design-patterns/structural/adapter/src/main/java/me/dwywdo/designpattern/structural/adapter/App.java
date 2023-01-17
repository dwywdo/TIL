package me.dwywdo.designpattern.structural.adapter;

import me.dwywdo.designpattern.structural.adapter.security.LoginHandler;
import me.dwywdo.designpattern.structural.adapter.security.UserDetailsService;

public class App {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);

        String login = loginHandler.login("euiyub", "euiyub");
        System.out.println(login);
    }
}
