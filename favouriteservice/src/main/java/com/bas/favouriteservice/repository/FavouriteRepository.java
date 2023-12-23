package com.bas.favouriteservice.repository;

import com.bas.favouriteservice.entity.Favourite;
import com.bas.favouriteservice.entity.id.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {



}
