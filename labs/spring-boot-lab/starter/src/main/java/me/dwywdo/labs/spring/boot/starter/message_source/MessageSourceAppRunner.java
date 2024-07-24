package me.dwywdo.labs.spring.boot.starter.message_source;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceAppRunner implements ApplicationRunner {

    private final MessageSource messageSource;

    public MessageSourceAppRunner(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Locale.setDefault(new Locale("en", "US"));

        printTimeMinutes(0,0);
        printTimeMinutes(0,1);
        printTimeMinutes(0,2);
        printTimeMinutes(1,0);
        printTimeMinutes(1,1);
        printTimeMinutes(1,2);
        printTimeMinutes(2,0);
        printTimeMinutes(2,1);
        printTimeMinutes(2,2);
    }

    private void printTimeMinutes(int remainingHours, int remainingMinutes) {
        System.out.println("remainingHours, remainingMinutes = " + remainingHours + ", " + remainingMinutes);
        if (remainingHours == 0) {
            System.out.println(messageSource.getMessage("time.minutes", new Integer[] { remainingMinutes }, Locale.getDefault()));
        } else {
            if (remainingMinutes == 0) {
                System.out.println(messageSource.getMessage("time.hours", new Integer[] { remainingHours }, Locale.getDefault()));
            } else {
                System.out.println(messageSource.getMessage("time.combined", new Integer[] { remainingHours, remainingMinutes}, Locale.getDefault()));
            }
        }
    }
}
