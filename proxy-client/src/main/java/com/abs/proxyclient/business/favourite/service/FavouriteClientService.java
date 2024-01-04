package com.abs.proxyclient.business.favourite.service;

import com.abs.proxyclient.business.favourite.model.FavouriteDto;
import com.abs.proxyclient.business.favourite.model.FavouriteId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "FAVOURITE-SERVICE", contextId = "favouriteClientService", path = "/favourite-service/api/favourites")
public interface FavouriteClientService {
    @GetMapping
    ResponseEntity<List<FavouriteDto>> findAll();

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDto> findById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate);

    @GetMapping("/find")
    public ResponseEntity<FavouriteDto> findById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId);

    @PostMapping
    public ResponseEntity<FavouriteDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDto favouriteDto);

    @PutMapping
    public ResponseEntity<FavouriteDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDto favouriteDto);

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate);

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId);
}
