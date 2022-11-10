package com.service.jwt;


import com.service.jwt.appUser.IAppUserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    @Autowired
    private IAppUserService appUserService;
    // key để mã hóa token.
    private static final String KEY_UNLOCK_TOKEN = "manchesterunited28";
    // thời gian token sống
    private static final long EXPIRE_TIME = 69600000000L;

    public String createToken(Authentication authentication) {
        // Lấy đối tượng từ đăng nhập
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME*1000))
                .signWith(SignatureAlgorithm.HS512,KEY_UNLOCK_TOKEN)
                .compact();
    }
    // ghi logger for token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(KEY_UNLOCK_TOKEN).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature -> Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid format token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }
    // Lấy tên user từ token:
    public String getUsernameFromJwtToken(String token){
        String username = Jwts.parser()
                .setSigningKey(KEY_UNLOCK_TOKEN)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }
}