package com.abs.proxyclient.business.favourite.model.response;

import com.abs.proxyclient.business.favourite.model.FavouriteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FavouriteFavouriteServiceCollectionDtoResponse {
    private Collection<FavouriteDto> collection;
}
