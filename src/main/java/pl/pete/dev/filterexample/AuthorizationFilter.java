package pl.pete.dev.filterexample;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//2. Filtr sprawdzi, czy sesja posiada ustawiony atrybut "login". Jeśli tak, to filtr przepuszcza żądanie do następnego ogniwa łańcucha filtrów.
// W innym wypadku, gdy login to "admin" a hasło to "password", ustawi sesji atrybut "login" o odczytanej wartości oraz "przepuści" dalej żądanie.
// Gdy podano nieprawidłowe dane, to filtr przerwie łańcuch oraz zwróci odpowiedź o statusie 401.
//3. Utwórz dokument jsp o nazwie login.jsp. Oferuje on formularz (przesyłający dane z wykorzystaniem metody POST) z polami login oraz hasło.
//??????????????????????????????????
// Formularz wysyła dane pod ścieżkę /authorized/welcome.html. Pod tą ścieżką użytkownikowi zostanie zwrócony dokument html z nagłówkiem o treści "Witaj <login>!".
//4. Użyj hibernate w taki sposób, aby dane tyczące się użytkowników były wybierane z bazy danych.
@WebFilter(urlPatterns = "/authorized/*")
public class AuthorizationFilter  extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) res;
        HttpSession session = httpReq.getSession();

        Object sessionLogin = session.getAttribute("login");
        if (sessionLogin != null){
            chain.doFilter(req, res);
        } else {
            String reqLogin = req.getParameter("login");
            String reqPassword = req.getParameter("password");
            if ("admin".equals(reqLogin) && "password".equals(reqPassword)){
                session.setAttribute("login", reqLogin);
                chain.doFilter(req, res);
            } else{
                req.getRequestDispatcher("login.jsp").forward(req, res);

//                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                res.getWriter().println("Nieprawidłowy login i/lub haslo" + res.getStatus());
            }
        }
//        String authorization = req.getHeader("Authorization");
//        String[] split = authorization.split(":");
//        if (split.length == 2){
//            System.out.println("Login: " + split[0] + " , password: " + split[1]);
//            chain.doFilter(req, res);
//        }

    }
}
