package user.login.userloginservice.config.jwt;

public interface JwtProperties {

    String SECRET = "jwt";

    int EXPIRATION_TIME = 864000000;

    String TOKEN_PREFIX = "Bearer ";

    String HEADER_STRING = "Authorization";
}
