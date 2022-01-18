package com.project.security.filters;

import com.google.gson.Gson;
import com.project.models.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for handling exceptions that occur in filters extends {@link OncePerRequestFilter}
 */
@Component
public class ExceptionFilter extends OncePerRequestFilter {

    /**
     * Overridden method handles exceptions and sends a response to the client
     * @param request request data
     * @param response response data
     * @param filterChain filter chain for passing data to the next filter
     * @throws ServletException if an error occurred while passing to the next filter
     * @throws IOException if an error occurred while passing to the next filter
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | IllegalArgumentException exception) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Gson gson = new Gson();
            response.getWriter().write(
                    gson.toJson(ExceptionBody.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(exception.getMessage()).build())
            );
        }
    }
}
