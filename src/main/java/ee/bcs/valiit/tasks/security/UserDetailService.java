package ee.bcs.valiit.tasks.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // teeb selle klassi springile kättesaadavaks ehk spring beani
public class UserDetailService implements UserDetailsService { // selles implementeeritud teenuses on kasutajate andmed

    @Autowired // injectib siia sisse järgmise asja
    private PasswordEncoder passwordEncoder; // siin peaks olema repo kus on mu kasutaja


    /* see on näide, edapsidi peab olema minu kasutjaa siin */
    @Override // loadUserByUsername meetodile kirjutame oma implementatsiooni
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.withUsername("test")
                .password(passwordEncoder.encode("test"))
                .roles("USER").build();
    }
}
