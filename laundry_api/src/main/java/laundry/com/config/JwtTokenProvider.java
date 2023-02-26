package laundry.com.config;

import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${jwt.secret}")
    private String secretKey;
	
	@Value("${jwt.expirationTime}")
    private long expirationTime;
	
	@Autowired
	private SqlSession sqlSession;
	
	public String createToken(String phoneNumber) {
        Claims claims = Jwts.claims().setSubject(phoneNumber);
        
        LinkedHashMap<String, String> user = sqlSession.selectOne("User.getUserByPhoneNumberWithoutPassword", phoneNumber);
        claims.put("user", user);
        
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
	
	public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();
            return now.before(expiration);
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSubjectFromJWT(String token) {
    	Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
