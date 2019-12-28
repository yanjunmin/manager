package top.westyle.manager.config.shiro;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import top.westyle.manager.utils.ResponseCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理(需要注册)
 * @date 2019-12-28 12:21:17
 * @author yjm
 */
public class ManagerExceptionHandler implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ManagerExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>();
        if (ex instanceof UnknownAccountException) {
            attributes.put("code", ResponseCode.unknown_account.getCode());
            attributes.put("msg", ResponseCode.unknown_account.getMsg());
        } else if (ex instanceof UnauthenticatedException) {
            attributes.put("code", ResponseCode.unauthenticated.getCode());
            attributes.put("msg", ResponseCode.unauthenticated.getMsg());
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", ResponseCode.unauthorized.getCode());
            attributes.put("msg", ResponseCode.unauthorized.getMsg());
        }else if (ex instanceof IncorrectCredentialsException) {
            attributes.put("code", ResponseCode.password_incorrect.getCode());
            attributes.put("msg", ResponseCode.password_incorrect.getMsg());
        } else if (ex instanceof LockedAccountException) {
            attributes.put("code", ResponseCode.forbidden_account.getCode());
            attributes.put("msg", ResponseCode.forbidden_account.getMsg());
        } else {
            logger.info(ex.getClass().getName());
            attributes.put("code", ResponseCode.error.getCode());
            attributes.put("msg", ResponseCode.error.getMsg());
        }

        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
