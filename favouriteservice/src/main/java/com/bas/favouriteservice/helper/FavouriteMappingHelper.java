package com.bas.favouriteservice.helper;

import com.bas.favouriteservice.dto.FavouriteDto;
import com.bas.favouriteservice.dto.ProductDto;
import com.bas.favouriteservice.dto.UserDto;
import com.bas.favouriteservice.entity.Favourite;

public interface FavouriteMappingHelper {
    public static FavouriteDto map(final Favourite favourite){
        return FavouriteDto.builder()
                .likeDate(favourite.getLikeDate())
                .productId(favourite.getProductId())
                .userId(favourite.getUserId())
                .userDto(UserDto.builder().userId(favourite.getUserId()).build())
                .productDto(ProductDto.builder().productId(favourite.getProductId()).build())
                .build();
    }

    public static Favourite map(final FavouriteDto favouriteDto){
        return Favourite.builder()
                .likeDate(favouriteDto.getLikeDate())
                .productId(favouriteDto.getProductId())
                .userId(favouriteDto.getUserId())
                .build();
    }
}
