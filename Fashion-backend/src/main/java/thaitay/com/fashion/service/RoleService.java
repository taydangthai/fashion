package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Role;
import thaitay.com.fashion.entity.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}
