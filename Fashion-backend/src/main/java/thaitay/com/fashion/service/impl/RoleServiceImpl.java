package thaitay.com.fashion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Role;
import thaitay.com.fashion.entity.RoleName;
import thaitay.com.fashion.repository.RoleRepository;
import thaitay.com.fashion.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
