package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTypesRepository extends JpaRepository<AssetType, Long> {
}
