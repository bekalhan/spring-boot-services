package com.bas.favouriteservice.service;

import com.bas.favouriteservice.dto.FavouriteDto;
import com.bas.favouriteservice.entity.id.FavouriteId;

import java.util.List;

public interface FavouriteService {
    List<FavouriteDto> getAllFavorites();
    FavouriteDto getFavouriteById(final FavouriteId favouriteId);
    FavouriteDto saveFavourite(final FavouriteDto favouriteDto);
    FavouriteDto updateFavourite(final FavouriteDto favouriteDto);
    void deleteFavouriteById(final FavouriteId favouriteId);
}