package com.example.taixenguguc.service;

import com.example.taixenguguc.dbo.request.AuthRequest;
import com.example.taixenguguc.dbo.request.IntrospectRequest;
import com.example.taixenguguc.dbo.response.AuthResponse;
import com.example.taixenguguc.dbo.response.IntrospectResponse;
import com.example.taixenguguc.entity.User;
import com.example.taixenguguc.exception.AppException;
import com.example.taixenguguc.exception.ErrorCode;
import com.example.taixenguguc.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthService {
    private final UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signedKey}")
    protected String SIGNED_KEY;

    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token=request.getToken();

        JWSVerifier verifier=new MACVerifier(SIGNED_KEY.getBytes());

        SignedJWT signedJWT=SignedJWT.parse(token);

        Date expityTime=signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified=signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid (verified && expityTime.after(new Date()))
                .build();
    }

    public AuthResponse authenticate(AuthRequest request){
        var user=userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);

        boolean authenticated= passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token=generateToken(user);


        return AuthResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    //    Hàm generate token
    private String generateToken(User user){
        //Bước 1; build token
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getId()) //Đại diện user đăng nhập
                .issuer("ka.com") //Xác định token issuer từ ai, thường là domain của mình
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(5, ChronoUnit.HOURS).toEpochMilli()
                )) //Thời hạn token
                .build();

        //Tạo payload cho token
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject=new JWSObject(header,payload);

        //Bước 2: Ký token
        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));//Thuật toán MacSigner: thuật toán ký token mà khóa ký và khóa giải mã trùng nhau, thuật toán này cần chuỗi 32bit -> lấy bằng cách lên web Encryption key generator để lấy chuỗi ngẫu nhiên.
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }
    public String extractUserId(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            return signedJWT.getJWTClaimsSet().getSubject(); //userId

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

}
