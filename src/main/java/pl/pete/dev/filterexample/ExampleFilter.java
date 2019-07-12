package pl.pete.dev.filterexample;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/dasvkcsoperplre")
public class ExampleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String myParam = req.getParameter("my-param");
        if (myParam != null) {
            chain.doFilter(req, res);
        } else {
            res.getWriter()
                    .println("Nie przekazano paramtru o nazwie my-param");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
