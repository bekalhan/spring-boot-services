package com.bas.userservice.service;

import com.bas.userservice.dto.CardDTO;
import com.bas.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    String addUserDetails(UserDTO userDTO,String username);
    String addCard(CardDTO cardDTO,String username);
}
