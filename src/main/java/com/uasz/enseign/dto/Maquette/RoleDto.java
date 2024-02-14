package com.uasz.enseign.dto.Maquette;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {
    private Long id;
    private String nom;
    private String description;
}
