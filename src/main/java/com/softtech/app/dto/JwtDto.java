package com.softtech.app.dto;

import com.softtech.app.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.Date;

/**
 * Created by N.Bimeri on 31/08/2021.
 */
@Data
@NoArgsConstructor
public class JwtDto {
    public final static String header = HttpHeaders.AUTHORIZATION;
    private String accessToken;
    private Boolean isExpired;
    private Boolean isValid;
    private Date expiredIn;
    private String type;
    private User userDetails;
}
