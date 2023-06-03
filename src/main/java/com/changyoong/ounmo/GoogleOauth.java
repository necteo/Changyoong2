package com.changyoong.ounmo;

import com.changyoong.ounmo.dto.SocialOAuthInfo;
import com.changyoong.ounmo.dto.UserAccessTokenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleOauth {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public UserAccessTokenDTO getAccessToken(String accessToken) {
        return new UserAccessTokenDTO(accessToken);
    }

    public ResponseEntity<String> requestUserInfo(UserAccessTokenDTO oAuthToken) {
        String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v3/userinfo";

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + oAuthToken.getAccessToken());
        log.info("Authorization: " + "Bearer " + oAuthToken.getAccessToken());

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class
        );

        log.info("response.getBody() = " + response.getBody());
        return response;
    }

    public SocialOAuthInfo getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException {
        SocialOAuthInfo userInfo = objectMapper.readValue(userInfoRes.getBody(), SocialOAuthInfo.class);
        log.info("Get User Info");
        return userInfo;
    }
}