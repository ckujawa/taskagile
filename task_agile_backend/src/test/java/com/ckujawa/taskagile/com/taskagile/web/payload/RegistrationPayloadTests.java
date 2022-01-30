package com.ckujawa.taskagile.com.taskagile.web.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ckujawa.taskagile.web.payload.RegistrationPayload;

public class RegistrationPayloadTests {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }
    
    @Test
    public void validateBlankPayloadFails() {
        RegistrationPayload payload = new RegistrationPayload();
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(3, violations.size());
    }

    @Test
    void validateShortUsernameFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("x");
        payload.setEmailAddress("x@somedomain.com");
        payload.setPassword("password");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
        ConstraintViolation<RegistrationPayload> violation = iter.next();
        assertEquals("Username must be between 2 and 50 characters.", violation.getMessage());
    }

    @Test
    void validateLongUsernameFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("superduperlongsenselessusernamethatnoonewouldeverusebecauseitspainfultotypein");
        payload.setEmailAddress("x@somedomain.com");
        payload.setPassword("password");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
        ConstraintViolation<RegistrationPayload> violation = iter.next();
        assertEquals("Username must be between 2 and 50 characters.", violation.getMessage());
    }

    @Test
    void validateInvalidEmailFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("username");
        payload.setEmailAddress("ReallyNiceTry.com");
        payload.setPassword("password");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
        ConstraintViolation<RegistrationPayload> violation = iter.next();
        assertEquals("Email address really should be an email address.", violation.getMessage());
    }

    @Test
    void validateLongEmailFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("username");
        payload.setEmailAddress(
                "wowthisisastupidlongemailaddressthatwouldneverfitonabusinesscardandnoonewouldeverwanttotypeinanyway@domain.com");
        payload.setPassword("password");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        
        assertEquals(2, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
    }
    
    @Test
    void validateLongPasswordFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("username");
        payload.setEmailAddress("ReallyNiceTry@goodjob.com");
        payload.setPassword("ThisP@ssw0rdisReallyReallyTooLongAndWillFailValidation");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
        ConstraintViolation<RegistrationPayload> violation = iter.next();
        assertEquals("Password must be between 8 and 30 characters.", violation.getMessage());
    }

    @Test
    void validateShortPasswordFails() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("username");
        payload.setEmailAddress("ReallyNiceTry@goodjob.com");
        payload.setPassword("pass");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        Iterator<ConstraintViolation<RegistrationPayload>> iter = violations.iterator();
        ConstraintViolation<RegistrationPayload> violation = iter.next();
        assertEquals("Password must be between 8 and 30 characters.", violation.getMessage());
    }

    @Test
    void validateGoodDataWorks() {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName("username");
        payload.setEmailAddress("ReallyNiceTry@goodjob.com");
        payload.setPassword("password");
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(0, violations.size());
    }
}
