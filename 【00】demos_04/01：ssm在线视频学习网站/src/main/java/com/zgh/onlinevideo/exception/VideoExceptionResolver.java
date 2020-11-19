package com.zgh.onlinevideo.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在线视频 服务器统一异常处理
 */
@Component
public class VideoExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("error");

        if (e instanceof UserException) {
            mv.addObject("message", e.getMessage());
        }

        return mv;

    }

}
