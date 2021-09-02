package com.example.demo.core.ExceptionHandlers;
/*
package com.example.demo.configuration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.core.Exceptions.BadRequestException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class GlobalHandlerException extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler, 
      Exception ex) {
        try {
            if (ex instanceof BadRequestException) {
                return handleIllegalArgument(
                  (BadRequestException) ex, response);
            }
        } 
        catch(IOException ioException){
            System.out.println("IOException");
        }
        catch (Exception handlerException) {
            System.out.println("Hello");
        }
        return null;
    }
    private ModelAndView handleIllegalArgument(BadRequestException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }
}
*/