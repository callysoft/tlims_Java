package com.tlimskech.marketplace.security.utils;

import com.tlimskech.marketplace.auth.user.User;
import com.tlimskech.marketplace.security.login.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppTokenUtil {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_EXPIRED = "exp";
    static final String CLAIM_KEY_SCOPE = "scope";

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    private final TimeProvider btsTimeProvider;
    @Value("${tlims.security.jwt.tokenSigningKey}")
    private String secret;
    @Value("${tlims.security.jwt.tokenExpirationTime}")
    private Long expiration;

    @Autowired
    public AppTokenUtil(TimeProvider btsTimeProvider) {
        this.btsTimeProvider = btsTimeProvider;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            username = null;
        }
        return username;
    }

    public List<String> getAuthoritiesFromToken(String token) {
        List<String> scopes;
        try {
            final Claims claims = getClaimsFromToken(token);
            scopes = (List<String>) claims.get(CLAIM_KEY_SCOPE);
        } catch (Exception e) {
            scopes = null;
        }
        return scopes;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(btsTimeProvider.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /*
     * private String generateAudience(Device device) { String audience =
     * AUDIENCE_UNKNOWN; if (device.isNormal()) { audience = AUDIENCE_WEB; }
     * else if (device.isTablet()) { audience = AUDIENCE_TABLET; } else if
     * (device.isMobile()) { audience = AUDIENCE_MOBILE; } return audience; }
     */
    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(UserContext userContext/* ,Device device */) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userContext.getEmail());
        /* claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device)); */
        final Date createdDate = btsTimeProvider.now();
        claims.put(CLAIM_KEY_CREATED, createdDate);
        List<String> auths = new ArrayList<>();
        for (GrantedAuthority authority : userContext.getAuthorities()) {
//            System.out.println(authority.getAuthority());
            auths.add(authority.getAuthority());
        }
        claims.put(CLAIM_KEY_SCOPE, auths);
        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        System.out.println("doGenerateToken " + createdDate);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, btsTimeProvider.now());
            refreshedToken = doGenerateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        // final Date created = getCreatedDateFromToken(token);
        // final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
        // && !isCreatedBeforeLastPasswordReset(created,
        // passenger.getLastPasswordResetDate()));
    }

}
