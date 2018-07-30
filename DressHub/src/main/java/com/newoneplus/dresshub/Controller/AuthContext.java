package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ApiResponseMessage;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.util.ApiFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

import javax.servlet.http.HttpServletResponse;

public class AuthContext {
    public static ApiResponseMessage askAuthorityAndAct(String userFromEntityModel, String token,
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
            }catch (MalformedJwtException e){
                apiResponseMessage = ApiFactory.notLogined();
            }
            if (userFromEntityModel.equals(user)) {
                actAfterAuthStrategy.act();
                apiResponseMessage = ApiFactory.accept();
            } else {
                apiResponseMessage = ApiFactory.noAuthority();
            }
            res.setStatus(apiResponseMessage.getResult_code());
        }
        return apiResponseMessage;
    }
}
