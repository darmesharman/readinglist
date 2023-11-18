package readingList.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import readingList.entity.Reader;

import java.util.Objects;

@Service
public class UserUtil {

    public Reader getCurrentUser() throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication) || !(authentication.getPrincipal() instanceof Reader)) {
            throw new UsernameNotFoundException("No authenticated user");
        }

        return (Reader) authentication.getPrincipal();
    }

}
