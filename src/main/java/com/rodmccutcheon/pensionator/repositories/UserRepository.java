package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
