package devPilot.backend.security;

import devPilot.backend.entity.Users;
import lombok.Getter;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Getter
public class AppUserPrincipal implements UserDetails {

    private final Users user;
    private Map<String, Object> attributes;

    public AppUserPrincipal(Users user,Map<String,Object>attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public UUID getId(){
        return user.getId();
    }

    public Users  getUsers(){
        return user;
    }

    public Map<String,Object> getAttributes(){
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")

        );
    }

        private String getName(){
        return  user.getId().toString();
       }

        @Override
        public @Nullable String getPassword() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
        }

        @Override
        public String getUsername() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        }
}