package ar.edu.itba.paw2019a2.webapp.auth;

import ar.edu.itba.paw2019a2.interfaces.service.UserService;
import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PawUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService us;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Either<User, Warnings> user = us.findByUsername(username);

        if(!user.isValuePresent())
            throw new UsernameNotFoundException("No such user");

        final Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(user.getValue().isEnabled()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_VALIDATED"));
        }
        return new PawUserDetails(username, user.getValue().getPassword(), user.getValue().getId(), user.getValue().isEnabled(), authorities);
        //return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
