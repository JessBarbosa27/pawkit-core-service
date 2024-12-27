package com.jessbarbosa.apps.pawkit_core_service.service.role;

import com.jessbarbosa.apps.pawkit_core_service.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role createRole(Role role);

    public Role updateRole(Long id, Role updatedRole);

    public void deleteRole(Long id);
}
