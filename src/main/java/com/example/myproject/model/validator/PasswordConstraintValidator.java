package com.example.myproject.model.validator;

import lombok.SneakyThrows;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {


    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Properties props = new Properties();
        InputStream inputStream = getClass().
                getClassLoader().getResourceAsStream("passay.properties");
        props.load(inputStream);
        MessageResolver resolver = new PropertiesMessageResolver(props);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
                // length between 4 and 16 characters
                new LengthRule(4, 16),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // at least one digit character
                //new CharacterRule(EnglishCharacterData.Digit, 1),
                // no whitespace
                new WhitespaceRule()
                ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return  true;
        }
//        List<String> messages = validator.getMessages(result);
//        String messageTemplate = String.join(",", messages);
//        context.buildConstraintViolationWithTemplate(messageTemplate)
//                .addConstraintViolation()
//                .disableDefaultConstraintViolation();
        return false;
    }
}
