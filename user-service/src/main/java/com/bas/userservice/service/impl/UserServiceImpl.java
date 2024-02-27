package com.bas.userservice.service.impl;

import com.bas.userservice.dto.CardDTO;
import com.bas.userservice.dto.UserDTO;
import com.bas.userservice.entity.Address;
import com.bas.userservice.entity.Card;
import com.bas.userservice.entity.User;
import com.bas.userservice.exception.InvalidActionException;
import com.bas.userservice.repository.UserRepository;
import com.bas.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public String addUserDetails(UserDTO userDTO, String username) {
        //fetch username from atuh service
        log.info("current logged in user is "+username);

        // her bir address dtoyu address entitysine çevir user'a ait
        List<Address>addressList=userDTO.getAddressDTOList().stream().map((addressDTO)->{
            return Address.builder()
                    .name(addressDTO.getName())
                    .phNo(addressDTO.getPhNo())
                    .state(addressDTO.getState())
                    .city(addressDTO.getCity())
                    .pinCode(addressDTO.getPinCode())
                    .addressType(addressDTO.getAddressType())
                    .build();
        }).toList();

        List<Card>cardList=userDTO.getCardDTOList().stream().map((cardDTO)-> Card.builder()
                .cardNumber(cardDTO.getCardNumber())
                .nameOnCard(cardDTO.getNameOnCard())
                .build()).toList();

        User user=User.builder()
                .username(username)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phNo(userDTO.getPhNo())
                .addressList(addressList)
                .cardList(cardList)
                .build();

        userRepository.save(user);
        return "User Details added successfully";
    }

    @Override
    public String addCard(CardDTO cardDTO, String username) {
        userRepository.findByUsername(username).
                orElseThrow(()->new InvalidActionException("Provide your details in the profile section to perform this action"));

        Optional<User> user=userRepository.findByUsername(username);
        List<Card>cardList=user.get().getCardList();
        cardList.add(Card.builder()
                .cardNumber(cardDTO.getCardNumber())
                .nameOnCard(cardDTO.getNameOnCard())
                .build());
        user.get().setCardList(cardList);
        userRepository.save(user.get());
        return "Card Details added successfully";
    }
}
