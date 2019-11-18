package top.westyle.manager.config.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.westyle.manager.utils.ResponseCode;
import top.westyle.manager.utils.Result;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yjm
 * @Date 2019-8-5 23:26:54
 * @Description 登录过滤器
 */
public class ShiroAuthenticationFilter extends PassThruAuthenticationFilter {
    private static Logger log = LoggerFactory.getLogger(ShiroAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                Result result = new Result(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                response.getWriter().append(JSONObject.toJSON(result).toString());
                response.getWriter().flush();
                response.getWriter().close();
                return false;
        }
    }
}
