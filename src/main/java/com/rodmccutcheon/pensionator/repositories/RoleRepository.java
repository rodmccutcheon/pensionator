package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
