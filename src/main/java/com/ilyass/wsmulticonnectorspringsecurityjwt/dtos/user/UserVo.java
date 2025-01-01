package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo implements UserDetails {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<RoleVo> authorities = new ArrayList<RoleVo>();
    private String email;
}
