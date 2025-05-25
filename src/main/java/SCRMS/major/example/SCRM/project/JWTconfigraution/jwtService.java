package SCRMS.major.example.SCRM.project.JWTconfigraution;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class jwtService {
    private String secretKey = "6D586E3272357538782F413F4428472B4B6250655368566D597133743677397A";;
//    public  jwtService() {
//        try {
//                KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk = keygen.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException(e);
//            }
//    }

    public  String getToken(String email){
        Map<String, Object> claim= new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return  Keys.hmacShaKeyFor(keyBytes);
        //Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    }

//    public String extractUsername(String token) {
//        return "";
//    }


//    public boolean validToken(String token, UserDetails userDetails) {
//        return  true;
//    }


    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.getSubject());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey()) // your secret key here
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 2. Validate Token
    public boolean validToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if token expired (without method reference)
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, claims -> claims.getExpiration());
    }

}
