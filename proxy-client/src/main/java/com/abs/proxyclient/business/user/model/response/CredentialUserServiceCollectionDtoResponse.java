package com.abs.proxyclient.business.user.model.response;

import java.io.Serializable;
import java.util.Collection;


import com.abs.proxyclient.business.user.model.CredentialDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CredentialUserServiceCollectionDtoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<CredentialDto> collection;

}