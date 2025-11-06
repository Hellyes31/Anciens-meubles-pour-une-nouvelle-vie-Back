package adawardrobe.service;


import adawardrobe.model.Color;
import adawardrobe.model.Photo;
import adawardrobe.model.Role;
import adawardrobe.repository.ColorRepository;
import adawardrobe.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(String id) {
        return roleRepository.findById(Long.parseLong(id));
    }

    public Optional<Role> updateRole(String id, Role roleDetails) {
        Optional<Role> role = roleRepository.findById(Long.parseLong(id));

        if (role.isPresent()) {
            Role existingRole = role.get();
            existingRole.setRole(roleDetails.getRole());

            Role updatedRole = roleRepository.save(existingRole);
            return Optional.of(updatedRole);
        }

        return Optional.empty();
    }
    public boolean deleteRole(String id) {
        Optional<Role> role = roleRepository.findById(Long.parseLong(id));

        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return true;
        }
        return false;
    }
}

