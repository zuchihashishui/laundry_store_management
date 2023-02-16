package laundry.com.config;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Value("${jwt.secret}")
    private String secretKey;
	
	@Value("${jwt.expirationTime}")
    private long expirationTime;
	
	@Autowired
	private SqlSession sqlSession;
	
	public String createToken(String phoneNumber, String userType) {
        Claims claims = Jwts.claims().setSubject(phoneNumber);
        
        LinkedHashMap<String, String> user = sqlSession.selectOne("User.getUserByPhoneNumberWithoutPassword", phoneNumber);
        claims.put("user", user);
                
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
	
	public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> parseTokenToUser(String token) {
		Map<String, Object> user = new LinkedHashMap<>();
		
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		if (claims != null) {
			user.putAll((Map<String, Object>) claims.get("user"));
			user.put("validity", claims.getExpiration());
		}
		return user;
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
}
