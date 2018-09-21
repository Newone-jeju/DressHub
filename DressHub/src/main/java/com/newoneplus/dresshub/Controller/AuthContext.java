package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.ApiResponseMessage;
import com.newoneplus.dresshub.Model.ResultMessage;
import com.newoneplus.dresshub.util.ApiFactory;
import io.jsonwebtoken.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletResponse;

public class AuthContext {
    public static void askAuthorityAndAct(String userFromEntityModel, String token, ActAfterAuthStrategy actAfterAuthStrategy) {
        HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        //TODO user수정됏을 때 기존의 토큰을 사용 불가능하게 막기
        ApiResponseMessage apiResponseMessage = null;
        if (token == null) {
            apiResponseMessage = ApiFactory.notLogined();
        } else {
            String user = null;
            try {
                Claims claims = getDecodedToken(token);
                user = (String) claims.get("uid");
                if (userFromEntityModel.equals(user)) {
                    actAfterAuthStrategy.act();
                    apiResponseMessage = ApiFactory.accept();
                } else {
                    apiResponseMessage = ApiFactory.noAuthority();
                }
            }catch (MalformedJwtException|UnsupportedJwtException|SignatureException|IllegalArgumentException e){
                apiResponseMessage = ApiFactory.notLogined();
//                return apiResponseMessage;
            } catch (Exception e) {
                res.setStatus(500, e.getMessage());
            }
        }
//        return apiResponseMessage;
        res.setStatus(apiResponseMessage.getResult_code());
    }

    public static void askLoginedAndAct(String token, ActAfterAuthStrategy actAfterAuthStrategy){
        HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        ApiResponseMessage apiResponseMessage = null;
        if (token == null) {
            apiResponseMessage = ApiFactory.notLogined();
        } else {
            String user = null;
            try {
                Claims claims = getDecodedToken(token);
                actAfterAuthStrategy.act();
                apiResponseMessage = ApiFactory.accept();
            }catch (MalformedJwtException|UnsupportedJwtException|SignatureException|IllegalArgumentException e){
                apiResponseMessage = ApiFactory.notLogined();
//                return apiResponseMessage;
            } catch (Exception e) {
                res.setStatus(500, e.getMessage());
            }
        }
//        return apiResponseMessage;
        res.setStatus(apiResponseMessage.getResult_code());
    }

    public static Claims getDecodedToken(String token){
        return Jwts.parser()
                .setSigningKey("Dresshub")
                .parseClaimsJws(token).getBody();
    }
}
