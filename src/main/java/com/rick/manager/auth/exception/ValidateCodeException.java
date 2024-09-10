package com.rick.manager.auth.exception;

import com.rick.manager.common.exception.ExceptionCodeEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * @author rick
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException() {
        super(ExceptionCodeEnum.VALIDATE_CODE_ERROR.getMessage());
    }
}
