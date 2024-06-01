package com.authservice.authservice.repository;

import com.authservice.authservice.dto.AuthenticationDTO;
import com.authservice.authservice.dto.UserDTO;
import com.authservice.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    @Query("""
            SELECT new com.authservice.authservice.dto.UserDTO(
            US.id,
            US.username,
            US.password
            )
            FROM User US
            WHERE US.username = :username
            """)
    UserDTO findByUsernameAndField(@Param("username") String username);

    @Query("""
        SELECT new com.authservice.authservice.dto.AuthenticationDTO(
            US.username,
            US.password,
            TK.token
        )
        FROM Token TK
        JOIN TK.user US
        WHERE US.id = :id
        AND (TK.token = :token AND TK.expired = false)
        ORDER BY TK.id DESC
    """)
    List<AuthenticationDTO> findTokenByUserid(@Param("id") Integer id, @Param("token") String token);

    @Query("SELECT u FROM User u WHERE u.id= :userId")
    User findUserById(Integer userId);


}