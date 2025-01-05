package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TokenVo implements Serializable {
    private String username;
    private String jwtToken;
    private List<String> roles = new ArrayList<>();

    // Getters and Setters
    public static class TokenVoBuilder {
        private String username;
        private String jwtToken;
        private List<String> roles = new ArrayList<>();

        public TokenVoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public TokenVoBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public TokenVoBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public TokenVo build() {
            TokenVo tokenVo = new TokenVo();
            tokenVo.username = this.username;
            tokenVo.jwtToken = this.jwtToken;
            tokenVo.roles = this.roles;
            return tokenVo;
        }
    }

    public static TokenVoBuilder builder() {
        return new TokenVoBuilder();
    }
}

