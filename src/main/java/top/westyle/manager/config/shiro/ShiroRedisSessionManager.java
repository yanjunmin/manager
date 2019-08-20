package top.westyle.manager.config.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

import static org.apache.shiro.web.servlet.ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE;

/**
 * @author yjm
 * @date 2019-8-20 23:53:14
 * @description 自定义shiro获取sessionid方法
 */
public class ShiroRedisSessionManager extends DefaultWebSessionManager {
    private static final Logger log = LoggerFactory.getLogger(ShiroRedisSessionManager.class);

    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        // 如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            log.info("请求头中获取");
            request.setAttribute(REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            log.info("默认方式获取sessionId");
            // 否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
