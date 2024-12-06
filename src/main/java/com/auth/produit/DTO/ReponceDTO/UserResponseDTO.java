package com.auth.produit.DTO.ReponceDTO;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class UserResponseDTO {

    private Long id;
    private String login;
    private Boolean active;
    private Collection<String> roles;

}
