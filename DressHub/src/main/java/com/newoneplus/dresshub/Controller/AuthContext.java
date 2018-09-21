package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ApiResponseMessage;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.util.ApiFactory;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletResponse;

public class AuthContext {
    public static void askAuthorityAndAct(String userFromEntityModel, String token,
                                                        HttpServletResponse res, ActAfterAuthStrategy actAfterAuthStrategy) {
        ApiResponseMessage apiResponseMessage = null;
        if (token == null) {
            apiResponseMessage = ApiFactory.notLogined();
        } else {
            String user = null;
            try {
                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey("Dresshub")
                        .parseClaimsJws(token);
                user = (String) claims.getBody().get("uid");
                if (userFromEntityModel.equals(user)) {
                    actAfterAuthStrategy.act();
                    apiResponseMessage = ApiFactory.accept();
                } else {
                    apiResponseMessage = ApiFactory.noAuthority();
                }
            }catch (MalformedJwtException|UnsupportedJwtException|SignatureException|IllegalArgumentException e){
                apiResponseMessage = ApiFactory.notLogined();
//                return apiResponseMessage;
            }
        }
//        return apiResponseMessage;
        res.setStatus(apiResponseMessage.getResult_code());
    }
}
