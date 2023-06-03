package com.changyoong.ounmo.service;

import com.changyoong.ounmo.GoogleOauth;
import com.changyoong.ounmo.dto.SocialOAuthInfo;
import com.changyoong.ounmo.dto.UserAccessTokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {
    private final GoogleOauth googleOauth;

    public SocialOAuthInfo oAuthLogin(String code) {
        SocialOAuthInfo info;
        try {
            //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
            UserAccessTokenDTO oAuthToken = googleOauth.getAccessToken(code);
            //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
            ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
            //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
            info = googleOauth.getUserInfo(userInfoResponse);
            log.info("User Email: " + info.getEmail() + ", User Name: " + info.getName());
        } catch (Exception e) {
            log.error(">>>" + e.getMessage());
            throw new IllegalStateException("OAUTH_LOGIN_FAILED");
        }
        return info;
    }
}