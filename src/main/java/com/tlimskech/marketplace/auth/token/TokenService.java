package com.tlimskech.marketplace.auth.token;

import com.tlimskech.marketplace.core.util.Utils;
import com.tlimskech.marketplace.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author dubic
 */
@Service
@Log4j2
public class TokenService {
    //        private final BizSecurityProperties props;
    private final TokenRepository tokenRepository;
    @Value("${email.verification.template}")
    private String linkExpiry;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token create(Token token) {
        Token created = this.tokenRepository.save(token);
        log.debug("{} Token created : {}", token.getTokenType(), token.getToken());
        return created;
    }


    public Token update(Token token) {
        Token found = this.tokenRepository.findById(token.getId()).orElseThrow(() -> new IllegalStateException("Resource not found"));
        found.copyForUpdate(token);
        return tokenRepository.save(found);
    }

    public Token createOrUpdate(Token token) {
        List<Token> tokenList = tokenRepository.findByDataRefAndTokenType(token.getDataRef(), token.getTokenType());
        if (!tokenList.isEmpty()) {
            tokenRepository.deleteAll(tokenList);
        }
        Token created = this.tokenRepository.save(token);
        log.debug("{} Token created : {}", token.getTokenType(), token.getToken());
        return created;
    }


    public void delete(Token token) {
        tokenRepository.delete(token);
    }


    public Optional<Token> findById(Long id) {
        return tokenRepository.findById(id);
    }

    public Page<Token> query(Token token, PageRequest p) {
        return (ObjectUtils.isEmpty(token)) ? tokenRepository.findAll(p) : tokenRepository.findAll(Example.of(token), p);
    }

    public Optional<Token> getUnusedToken(String tok, String dataRef) {
        return this.tokenRepository.findByTokenAndDataRefAndUsedIsFalse(tok, dataRef).stream().findFirst();
    }

    public Boolean isValidToken(Token token) {
        //check expired
        return token.isExpired();
    }

    public void invalidateToken(Token token) {
        token.invalidate();
        this.tokenRepository.save(token);
    }

    public Token buildToken(TokenType tokenType, String dataRef) {
        Token built = new Token();
        built.setDataRef(dataRef);
        built.setTokenType(tokenType);
        built.setUsed(false);
        if (TokenType.PASSWORD_RESET_LINK == tokenType) {
            built.setExpiryDate(LocalDateTime.now().plusHours(24));
            built.setToken(UUID.randomUUID().toString());
        }
        if (TokenType.PASSWORD_RESET_TOKEN == tokenType) {
            built.setExpiryDate(LocalDateTime.now().plusMinutes(10));
            built.setToken(org.apache.commons.lang3.StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0"));
        }
        if (TokenType.SOFT_TOKEN == tokenType) {
            built.setExpiryDate(LocalDateTime.now().plusMinutes(10));
            built.setToken(Utils.generateSoftToken(6));
        }
        if (TokenType.FIRST_LOGIN == tokenType) {
            built.setExpiryDate(LocalDateTime.now().plusMinutes(10));
            built.setToken(org.apache.commons.lang3.StringUtils.leftPad(new Random().nextInt(1000000) + "", 6, "0"));
        }
        return create(built);
    }

    /**
     * Generates a soft token of the number of digits specified. the token expiry date is set to a default 10 minutes. this can
     * be overridden in properties = biz.security.tokens.softExpiryMins
     *
     * @param dataRef a reference to tie to the token generated.
     * @param size    the number of digits in token
     * @return
     */
    public Token buildSoftToken(String dataRef, int size) {
        System.out.println("data ref :: $dataRef size:: $size");
        Token built = new Token();
        built.setDataRef(dataRef);
        built.setTokenType(TokenType.SOFT_TOKEN);
        built.setUsed(false);
        System.out.println("props :: ${this.props}");
        log.debug("Soft token expiry :: ${this.props.tokens.softExpiryMins}");
        built.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        built.setToken(Utils.generateSoftToken(size));
        built.setCreateDate(new Date());
        return createOrUpdate(built);
    }

    public void verifyToken(String userToken, String dataref) {
        Token token = this.getUnusedToken(userToken, dataref).orElseThrow(() -> new ApplicationException("Invalid token"));
        log.debug("Token found {}", token);
        if (token.isExpired()) {
            throw new ApplicationException("Token has expired");
        }
        this.invalidateToken(token);
    }

    public Token getUnusedUnexpiredToken(String tok, String dataRef) {
        Token token = getUnusedToken(tok, dataRef).orElseThrow(() -> new ApplicationException("Invalid token"));
        log.debug("Token found {}", token);
        if (token.isExpired()) {
            throw new ApplicationException("Token has expired");
        }
        return token;
    }


}
