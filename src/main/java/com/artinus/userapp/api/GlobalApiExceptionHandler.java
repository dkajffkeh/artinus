package com.artinus.userapp.api;

import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.payload.ApiResult;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    @ExceptionHandler(ArtinusException.class)
    public ApiResult<String> handleUserAlreadyExistsException(ArtinusException ex) {
        return ex.toApiResult();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ApiResult<?> handle(BindException e) {
        String errorMessage = e.getAllErrors().get(0).getDefaultMessage();

        List<String> errorMessages = new ArrayList<>();

        e.getAllErrors().forEach(error -> {
            errorMessages.add(error.getDefaultMessage());
        });

        return ApiResult.badRequest(errorMessages, errorMessage);
    }
}
