package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.entities.User;
import com.danielassisdesenvolvedor.projetojavaorm.projections.UserDetailsProjection;

public class ClientDTO {
    private Long id;
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(User user) {
        id = user.getId();
        name = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}