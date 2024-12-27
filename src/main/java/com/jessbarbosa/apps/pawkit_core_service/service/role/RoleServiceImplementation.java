package com.jessbarbosa.apps.pawkit_core_service.service.role;

import com.jessbarbosa.apps.pawkit_core_service.exception.RoleNotFoundException;
import com.jessbarbosa.apps.pawkit_core_service.model.Role;
import com.jessbarbosa.apps.pawkit_core_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation {

    private final RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role updatedRole) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        role.setName(updatedRole.getName());
        role.setDescription(updatedRole.getDescription());
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
