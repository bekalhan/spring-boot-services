package com.bas.userservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "verification_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VerificationToken extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id", unique = true, nullable = false, updatable = false)
    private Integer verificationTokenId;

    @Column(name = "verif_token")
    private String token;

    /*@JsonFormat(pattern = AppConstant.LOCAL_DATE_FORMAT, shape = Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_FORMAT)*/
    @Column(name = "expire_date")
    private LocalDate expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credential_id")
    private Credential credential;
}
