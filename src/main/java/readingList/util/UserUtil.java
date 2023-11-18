package readingList.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import readingList.entity.User;

import java.util.Objects;

@Service
public class UserUtil {

    public User getCurrentUser() throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !(authentication.getPrincipal() instanceof User)) {
            throw new UsernameNotFoundException("No authenticated user");
        }

        return (User) authentication.getPrincipal();
    }

}
