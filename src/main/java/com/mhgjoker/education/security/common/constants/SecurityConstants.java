package com.mhgjoker.education.security.common.constants;

public final class SecurityConstants {

    public static final String ROLE_CLAIMS = "role";

    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    public static final String TOKEN_HEADER = "Authorization";

    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    public static final String TOKEN_PREFIX = "Bearer";

    public static final String[] SYSTEM_WHITELIST = {
            "api/auth/**","api/forgotPassword/**","test/**","api/auth/verifyRegister/**","api/animes/**","api/genres","api/promotion_videos",
            "api/characters/**", "api/voice_actors/**"
    };

    private SecurityConstants() {

    }
}
