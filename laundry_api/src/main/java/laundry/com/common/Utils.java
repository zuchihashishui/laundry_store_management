package laundry.com.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class Utils {
	
	public static String getRequestUri() {
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
	    ServletRequestAttributes servlReqAttr = (ServletRequestAttributes)reqAttr;
	    HttpServletRequest req = servlReqAttr.getRequest();
	    return req.getRequestURI().toString();
	}
	
	public static String getRequestUri(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI().toString();
    }

}
