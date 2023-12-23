package com.bas.userservice.service.impl;

import com.bas.userservice.dto.UserDto;
import com.bas.userservice.entity.User;
import com.bas.userservice.exception.ResourceAlreadyExistException;
import com.bas.userservice.exception.ResourceNotFoundException;
import com.bas.userservice.helper.UserMapperHelper;
import com.bas.userservice.repository.UserRepository;
import com.bas.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapperHelper::map)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer userId) {
        return this.userRepository.findById(userId)
                .map(UserMapperHelper::map)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if(user != null){
            throw new ResourceAlreadyExistException("User","email",user.getEmail());
        }

        return UserMapperHelper.map(this.userRepository.save(UserMapperHelper.map(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return UserMapperHelper.map(this.userRepository.save(UserMapperHelper.map(userDto)));
    }

    @Override
    public UserDto update(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        User mapToUser = UserMapperHelper.map(userDto);
        User updatedUser = userRepository.save(mapToUser);

        return UserMapperHelper.map(updatedUser);

    }

    @Override
    public void deleteById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));

        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByUsername(String username) {
        return UserMapperHelper.map(this.userRepository.findByCredentialUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User","name",username)));
    }
}
