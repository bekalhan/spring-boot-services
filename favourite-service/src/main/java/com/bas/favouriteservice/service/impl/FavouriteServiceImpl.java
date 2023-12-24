package com.bas.favouriteservice.service.impl;

import com.bas.favouriteservice.constant.AppConstant;
import com.bas.favouriteservice.dto.FavouriteDto;
import com.bas.favouriteservice.dto.ProductDto;
import com.bas.favouriteservice.dto.UserDto;
import com.bas.favouriteservice.entity.id.FavouriteId;
import com.bas.favouriteservice.exception.FavouriteNotFoundException;
import com.bas.favouriteservice.helper.FavouriteMappingHelper;
import com.bas.favouriteservice.repository.FavouriteRepository;
import com.bas.favouriteservice.service.FavouriteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FavouriteServiceImpl implements FavouriteService {

    FavouriteRepository favouriteRepository;
    private final RestTemplate restTemplate;


    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, RestTemplate restTemplate) {
        this.favouriteRepository = favouriteRepository;
        this.restTemplate = restTemplate;

    }

    @Override
    public List<FavouriteDto> getAllFavorites() {
        return favouriteRepository.findAll().stream().map(FavouriteMappingHelper::map).peek(f -> {
            f.setUserDto(restTemplate.getForObject(
                    AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL + "/" + f.getUserId(),
                    UserDto.class)
            );
            f.setProductDto(restTemplate.getForObject(
                    AppConstant.DiscoveredDomainsApi.PRODUCT_SERVICE_API_URL + "/" + f.getProductId(),
                    ProductDto.class));
    }).collect(Collectors.toList());
    }


    @Override
    public FavouriteDto getFavouriteById(FavouriteId favouriteId) {
        return this.favouriteRepository.findById(favouriteId)
                .map(FavouriteMappingHelper::map)
                .map(f -> {
                    f.setUserDto(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .USER_SERVICE_API_URL + "/" + f.getUserId(), UserDto.class));
                    f.setProductDto(this.restTemplate
                            .getForObject(AppConstant.DiscoveredDomainsApi
                                    .PRODUCT_SERVICE_API_URL + "/" + f.getProductId(), ProductDto.class));
                    return f;
                })
                .orElseThrow(() -> new FavouriteNotFoundException(
                       String.format("Favourite with id: [%s] not found!", favouriteId)));
    }

    @Override
    public FavouriteDto saveFavourite(final FavouriteDto favouriteDto) {
        return FavouriteMappingHelper.map(this.favouriteRepository
                .save(FavouriteMappingHelper.map(favouriteDto)));
    }

    @Override
    public FavouriteDto updateFavourite(final FavouriteDto favouriteDto) {
        return FavouriteMappingHelper.map(this.favouriteRepository
                .save(FavouriteMappingHelper.map(favouriteDto)));
    }

    @Override
    public void deleteFavouriteById(final FavouriteId favouriteId) {
        this.favouriteRepository.deleteById(favouriteId);
    }
}
