package nz.net.osnz.demos.j2ee.filter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

/**
 *
 * @author Kefeng Deng (deng@51any.com) - Created on 11/03/15.
 */
@WebFilter(urlPatterns = "/*")
class MyFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(MyFilter)

  FilterConfig config

  @Override
  void init(FilterConfig filterConfig) throws ServletException {
    this.config = filterConfig
  }

  @Override
  void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("In MyFilter")
    String requestURI = request instanceof HttpServletRequest ? ((HttpServletRequest) request).getRequestURI().toString() : "N/A"
    if ("/my-filter".equalsIgnoreCase(requestURI)) {
      response.status = 400
      response.writer.write("Block by filter")
    } else {
      chain.doFilter(request, response)
    }
  }

  @Override
  void destroy() {

  }
}
