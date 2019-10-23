package stu.napls.copdmanage.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import stu.napls.copdmanage.core.response.Response;

/**
 * @author Yepeng Ding
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = SystemException.class)
    public Response systemErrorHandler(SystemException e) {
        logger.error(e.getMessage());
        return Response.failure(e.getCode(), e.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = TokenException.class)
    public Response rtErrorHandler(TokenException e) {
        logger.error(e.getMessage());
        return Response.failure(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response errorHandler(Exception e) {
        logger.error(e.getMessage());
        return Response.failure("Internal Error");
    }


}
