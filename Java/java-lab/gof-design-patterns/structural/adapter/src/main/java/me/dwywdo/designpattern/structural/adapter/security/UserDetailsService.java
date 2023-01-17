package me.dwywdo.designpattern.structural.adapter.security;

/**
 * Target Interface #2
 */
public interface UserDetailsService {


    public UserDetails loadUser(String username);
}
