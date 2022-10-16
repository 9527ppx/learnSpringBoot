package com.wu.boot.filter;


import com.wu.boot.cache.RedisCache;
import com.wu.boot.pojo.LoginUser;
import com.wu.boot.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //获取token
        //从请求头中获取
        String token = request.getHeader("token");

        //token有可能是空的 有内容是true
        if (!StringUtils.hasText(token)){
            //token没有内容直接放行，后面的filter会处理
            filterChain.doFilter(request,response);
            //return 是不让回来的代码执行下面的内容
            return;
        }
        //解析token获取userid
        String userId;
        try {
            //解析jwt生成的userid
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token解析失败");
        }
        //通过userId 从redis中获取LoginUser对象
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        //存入SecurityContextHolder
        //有可能redis里没有这个userId的信息,不为空的时候再处理
        if (Objects.nonNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //TODO 还需要把权限信息也封装进去  authorities
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        //需要Authentication对象
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //最后放行
        filterChain.doFilter(request,response);
    }
}
