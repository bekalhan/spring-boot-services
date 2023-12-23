package com.bas.favouriteservice.controller;

import com.bas.favouriteservice.constant.AppConstant;
import com.bas.favouriteservice.dto.FavouriteDto;
import com.bas.favouriteservice.entity.id.FavouriteId;
import com.bas.favouriteservice.service.FavouriteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouriteController {
    @Autowired
    private final FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<List<FavouriteDto>> findAll() {
        var allCarts = favouriteService.getAllFavorites();
        return ResponseEntity.ok(allCarts);
    }

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDto> findById(
                    @PathVariable("userId") final String userId,
                    @PathVariable("productId") final String productId,
                    @PathVariable("likeDate") final String likeDate)
    {
        try {
            return ResponseEntity.ok(this.favouriteService.getFavouriteById(
                    new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                            LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT)))));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find")
    public ResponseEntity<FavouriteDto> findById(@RequestBody
                                                 @NotNull(message = "Input must not be NULL")
                                                 @Valid final FavouriteId favouriteId) {
        return ResponseEntity.ok(this.favouriteService.getFavouriteById(favouriteId));
    }

    @PostMapping
    public ResponseEntity<FavouriteDto> save(@RequestBody
                                             @NotNull(message = "Input must not be NULL")
                                             @Valid final FavouriteDto favouriteDto) {
        return ResponseEntity.ok(this.favouriteService.saveFavourite(favouriteDto));
    }

    @PutMapping
    public ResponseEntity<FavouriteDto> update(@RequestBody
                                               @NotNull(message = "Input must not be NULL")
                                               @Valid final FavouriteDto favouriteDto) {
        return ResponseEntity.ok(this.favouriteService.updateFavourite(favouriteDto));
    }

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") final String userId,
                                              @PathVariable("productId") final String productId,
                                              @PathVariable("likeDate") final String likeDate) {
        favouriteService.deleteFavouriteById(
                new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                        LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT)))
        );
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody
                                              @NotNull(message = "Input must not be NULL")
                                              @Valid final FavouriteId favouriteId) {
        favouriteService.deleteFavouriteById(favouriteId);
        return ResponseEntity.ok(true);
    }



}
