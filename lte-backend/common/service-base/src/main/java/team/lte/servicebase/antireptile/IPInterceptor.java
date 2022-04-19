package team.lte.servicebase.antireptile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IPInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
        @NonNull Object handler) {
        // IP
        String ip = IPUtils.getIpAddr(request);
        // 考虑到如果对方是爬虫的可能，那么请求也许会很多，手动加分割线区分每次请求，后头看日志可以直观些
        log.info(" ┌───────────────────────────────────────────────────────────────────────");
        // log.info(" │Time: {}", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        log.info(" │IP: {}", ip);
        log.info(" │URI: {}", request.getRequestURI());
        // log.info(" │Method: {}", request.getMethod());
        // log.info(" │CharacterEncoding: {}", request.getCharacterEncoding());
        // log.info(" │QueryString: {}", request.getQueryString());
        // Enumeration<String> headers = request.getHeaderNames();
        // // Header部分
        // while (headers.hasMoreElements()) {
        //     String headerName = headers.nextElement();
        //     String header = request.getHeader(headerName);
        //     log.info(" │-{}: {}", headerName, header);
        // }
        log.info(" └───────────────────────────────────────────────────────────────────────");
        return false;
    }
}
