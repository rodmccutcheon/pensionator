package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
