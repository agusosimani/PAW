package ar.edu.itba.paw.webapp.auth;

import ar.edu.itba.paw.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PawUserDetails extends org.springframework.security.core.userdetails.User {
    private int id;

    public PawUserDetails(String username, String password, int id, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, isEnabled, true, true, true, authorities);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
