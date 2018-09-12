package com.qslion.authority.custom.service.impl;

import com.qslion.authority.core.entity.AuRole;
import com.qslion.authority.core.entity.AuUser;
import com.qslion.authority.core.entity.AuUserGroup;
import com.qslion.authority.custom.dao.AuUserRepository;
import com.qslion.authority.custom.service.AuUserService;
import com.qslion.framework.service.impl.GenericServiceImpl;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/30 19:19.
 */
@Service
public class AuUserServiceImpl extends GenericServiceImpl<AuUser, Long> implements AuUserService, UserDetailsService {

    @Autowired
    AuUserRepository auUserRepository;

    @Override
    public AuUser addAuUser(AuUser auUser) {
        return auUserRepository.save(auUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuUser auUser = auUserRepository.findUserByUsername(username);
        if (auUser != null) {
            Set<AuUserGroup> userGroups = auUser.getUserGroups();
            for (AuUserGroup userGroup : userGroups) {
                Set<AuRole> roles = userGroup.getRoles();
                auUser.setAuthorities(roles);
            }
            return auUser;
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");

        }
    }
}
