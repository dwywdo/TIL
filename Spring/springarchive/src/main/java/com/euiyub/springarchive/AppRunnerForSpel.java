package com.euiyub.springarchive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class AppRunnerForSpel implements ApplicationRunner {

    // Expression with #
    @Value("#{1 + 1}")
    int value;

    @Value("#{'hello ' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    // Properties with $
    @Value("${my.value}")
    int myValue;

    // Property inside Expression
    @Value("#{${my.value} eq 200}")
    boolean isMyValue100;

    @Value("#{'spring'}")
    String spring;

    @Value("#{sample.data}")
    int sampleData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================");
        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        System.out.println(myValue);
        System.out.println(isMyValue100);
        System.out.println(sampleData);

        final ExpressionParser parser = new SpelExpressionParser();
        final Expression expression = parser.parseExpression("2 + 100");
        // Note. conversionService is being used here
        final Integer value = expression.getValue(Integer.class);
        System.out.println(value);

    }
}
