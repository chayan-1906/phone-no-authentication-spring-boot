package com.padmanabhasmac.phonenoauth.phonenoauthentication.resource;

import com.padmanabhasmac.phonenoauth.phonenoauthentication.dto.PasswordResetRequestDTO;
import com.padmanabhasmac.phonenoauth.phonenoauthentication.services.TwilioOTPService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOTPHandler {

    @Autowired
    private TwilioOTPService twilioOTPService;

    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PasswordResetRequestDTO.class)
                .flatMap(passwordResetRequestDTO -> twilioOTPService.sendOTPPasswordReset(passwordResetRequestDTO))
                .flatMap(passwordResetResponseDTO -> ServerResponse.status(HttpStatus.SC_OK)
                        .body(BodyInserters.fromValue(passwordResetResponseDTO)));
    }

    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PasswordResetRequestDTO.class)
                .flatMap(passwordResetRequestDTO -> twilioOTPService.validateOTP(passwordResetRequestDTO.getOneTimePassword(), passwordResetRequestDTO.getUserName()))
                .flatMap(passwordResetResponseDTO -> ServerResponse.status(HttpStatus.SC_OK)
                        .bodyValue(passwordResetResponseDTO));
    }
}
