package life.tree3.trunk.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>描述: Json Web token的工具类;
 * 默认项目读取application.properties文件中的配置
 * 密钥：app.jwtSecret
 * 有效时间：app.effectiveTime
 * </p >
 * <p>描述:  Base64要求把每三个8Bit的字节转换为四个6Bit的字节（3*8 = 4*6 = 24），然后把6Bit再添两位高位0，组成四个8Bit的字节，也就是说，转换后的字符串理论上将要比原来的长1/3。
 * 标准的Base64并不适合直接放在URL里传输，因为URL编码器会把标准Base64中的“/”和“+”字符变为形如“%XX”的形式
 * 为解决此问题，可采用一种用于URL的改进Base64编码，它不在末尾填充'='号，并将标准Base64中的“+”和“/”分别改成了“*”和“-”.
 * https://blog.csdn.net/qq_35725321/article/details/52126402
 * <p>
 * JSON Web Token structure:Token = base64UrlEncode(Header) + '.' + base64UrlEncode(Payload) + '.' + base64UrlEncode(Signature)
 * Header
 * header typically consists of two parts: the type of the token, which is JWT, and the signing algorithm being used, such as HMAC SHA256 or RSA.
 * >>>:{"alg":"HS256","typ":"JWT" };
 * <p>
 * Payload
 * >>>:{"sub": "1234567890","name": "John Doe","admin": true}
 * payload contains the claims. Claims are statements about an entity (typically, the user) and additional data.
 * <p>
 * Signature
 * For example use the HMAC SHA256 algorithm, the signature will be created in the following way:
 * >>>:HMACSHA256( base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 * To create the signature part you have to take the encoded header, the encoded payload, a secret, the algorithm specified in the header, and sign that.</p>
 * <a>@Author: Rupert</ a>
 * <p>创建时间: 2022/12/2 0002 9:03 </p >
 */
public class JWTUtil {
    public static Logger log = LoggerFactory.getLogger(JWTUtil.class);

    private static final String FILE_NAME = "application.properties";
    private static final String JWT_SECRET_KEY = "app.jwtSecret";
    private static final String EFFECTIVE_KEY = "app.effectiveTime";
    public static final String OWNER_ID = "userId";//有点冗余
    public static final String OWNER_NAME = "username";
    public static final String OWNER_PASSWORD = "9Hpeuhnv";

    /**
     * 加载配置文件中配置的密钥和有效时间
     */
    static {
        String path = Objects.requireNonNull(JWTUtil.class.getClassLoader().getResource(FILE_NAME)).getPath();
        try (FileInputStream is = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(is);
            Object secret = prop.get(JWT_SECRET_KEY);
            Object effective = prop.get(EFFECTIVE_KEY);
            if (!isEffectiveStr((String) secret)) {
                throw new IllegalArgumentException("未设置jwt密钥");
            }
            jwtSecret = (String) secret;

            if (isEffectiveStr((String) effective)) {
                effectiveTime = Long.parseLong((String) effective);
            }
        } catch (IOException e) {
            log.debug("jwt密钥和有效时间加载失败");
            e.printStackTrace();
        }
    }

    /**
     * JWT 密钥；
     * required
     */
    private static String jwtSecret;

    /**
     * JWT有效时间,默认2min
     * 1000*60*60=1小时
     */
    private static long effectiveTime = 120000;

    /**
     * 生成JWT token
     *
     * @param userId
     * @return
     */
    public static String createToken(Integer userId) {
        String token = null;
        try {
            String issuer = Inet4Address.getLocalHost().toString();
            long current = System.currentTimeMillis();
            token = JWT.create()
                    .withJWTId(String.valueOf(UUID.nameUUIDFromBytes(issuer.getBytes(StandardCharsets.UTF_8))))//token的唯一标识
                    .withIssuedAt(new Date(current))//token的签发时间
                    .withIssuer(issuer)//token的签发者
                    .withExpiresAt(new Date(current + effectiveTime))//token过期时间
                    .withAudience(String.valueOf(userId))//token是给谁的,（逻辑上是单体）

                    .withClaim(OWNER_ID, userId)//payload
                    .sign(Algorithm.HMAC256(jwtSecret));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 生成JWT token
     *
     * @param userId
     * @param username
     * @return
     */
    public static String createToken(Integer userId, String username) {
        String token = null;
        try {
            String issuer = Inet4Address.getLocalHost().toString();
            String jwtId = String.valueOf(UUID.nameUUIDFromBytes(issuer.getBytes(StandardCharsets.UTF_8)));

            long current = System.currentTimeMillis();
            token = JWT.create()
                    .withJWTId(jwtId)//token的唯一标识
                    .withIssuedAt(new Date(current))//token的签发时间
                    .withIssuer(issuer)//token的签发者
                    .withExpiresAt(new Date(current + effectiveTime))//token过期时间
                    .withAudience(String.valueOf(userId))//token是给谁的,（逻辑上是单体）

                    .withClaim(OWNER_ID, userId)//payload
                    .withClaim(OWNER_NAME, username)//payload
                    .sign(Algorithm.HMAC256(jwtSecret));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 生成JWT token
     *
     * @param payloadClaims{userId:123,username:"xx",roles:"[]"} 在jwt中存储的非隐私性信息
     * @return
     */
    public static String createToken(Map<String, Object> payloadClaims) {
        if (payloadClaims == null || payloadClaims.get(OWNER_ID) == null || payloadClaims.get(OWNER_NAME) == null) {
            throw new IllegalArgumentException("创建token所需要的参数为null/缺少必要的参数[userId,username]");
        }
        long current = System.currentTimeMillis();
        String token = null;
        try {
            String issuer = Inet4Address.getLocalHost().toString();
            token = JWT.create()
                    .withJWTId(String.valueOf(UUID.nameUUIDFromBytes(issuer.getBytes(StandardCharsets.UTF_8))))//token的唯一标识
                    .withIssuedAt(new Date(current))//token的签发时间
                    .withIssuer(issuer)//token的签发者
                    //.withNotBefore(new Date())//token生效时间
                    .withExpiresAt(new Date(current + effectiveTime))//token过期时间
                    //.withSubject("逻辑上是群体")//token是给谁的,（逻辑上是群体）
                    .withAudience(String.valueOf(payloadClaims.get(OWNER_ID)))//token是给谁的,（逻辑上是单体）

                    .withPayload(payloadClaims)//payload
                    .sign(Algorithm.HMAC256(jwtSecret));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return 解密后的 token对象，可从中获取payload信息
     */
    public static DecodedJWT verify(String token) {
        try {
            Algorithm signer = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(signer).build();
            return verifier.verify(token);// 校验不通过会抛出异常;//判断合法的标准：1. 头部和荷载部分没有篡改过 2. 没有过期
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            return null;
        } catch (JWTVerificationException e) {
            log.info("JWTVerificationException");
            return null;
        }
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpire(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().getTime() < System.currentTimeMillis();
    }

    private static boolean isEffectiveStr(String input) {
        return !(input == null || "".equals(input));
    }
}
