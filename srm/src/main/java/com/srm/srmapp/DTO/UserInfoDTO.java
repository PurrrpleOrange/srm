package com.srm.srmapp.DTO;

import com.srm.srmapp.model.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String username;
    private Role role;
}
