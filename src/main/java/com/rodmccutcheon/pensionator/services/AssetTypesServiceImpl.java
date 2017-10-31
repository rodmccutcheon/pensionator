package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.AssetType;
import com.rodmccutcheon.pensionator.repositories.AssetTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypesServiceImpl implements AssetTypesService {

    private AssetTypesRepository assetTypesRepository;

    @Autowired
    public AssetTypesServiceImpl(AssetTypesRepository assetTypesRepository) {
        this.assetTypesRepository = assetTypesRepository;
    }

    @Override
    public List<AssetType> listAllAssetTypes() {
        return assetTypesRepository.findAll();
    }
}
