package com.schibsted.spain.friends.service.legacy;

import com.schibsted.spain.friends.application.common.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbsctractLegacyController {

    @ExceptionHandler({IllegalArgumentException.class, ApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException() {
    }
}
