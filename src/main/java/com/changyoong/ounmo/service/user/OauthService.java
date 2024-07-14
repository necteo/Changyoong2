package com.changyoong.ounmo.service.user;

import com.changyoong.ounmo.GoogleOauth;
import com.changyoong.ounmo.dto.user.SocialOAuthInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {
    private final GoogleOauth googleOauth;

    public SocialOAuthInfo oAuthVerify(String code) {
        SocialOAuthInfo info;
        try {
            String accessToken = googleOauth.getAccessToken(code);
            //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
            ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(accessToken);
            info = googleOauth.getUserInfo(userInfoResponse);
            log.info("User Email: {}, User Name: {}", info.getEmail(), info.getName());
        } catch (Exception e) {
            log.error(">>>{}", e.getMessage());
            throw new IllegalStateException("OAUTH_VERIFY_FAILED");
        }
        return info;
    }
}