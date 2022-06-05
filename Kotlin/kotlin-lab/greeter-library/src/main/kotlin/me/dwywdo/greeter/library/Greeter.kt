package me.dwywdo.greeter.library

import me.dwywdo.greeter.library.GreeterConfigParams.AFTERNOON_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.EVENING_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.MORNING_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.NIGHT_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.USER_NAME
import java.time.LocalDateTime

class Greeter(greetingConfig: GreetingConfig) {
    private val greetingConfig: GreetingConfig

    init {
        this.greetingConfig = greetingConfig
    }

    @JvmOverloads
    fun greet(localDateTime: LocalDateTime = LocalDateTime.now()): String {
        val name: String = greetingConfig.getProperty(USER_NAME)
        return when (localDateTime.hour) {
            in 5..11 -> {
                java.lang.String.format("Hello %s, %s", name, greetingConfig[MORNING_MESSAGE])
            }
            in 12..16 -> {
                java.lang.String.format("Hello %s, %s", name, greetingConfig[AFTERNOON_MESSAGE])
            }
            in 17..19 -> {
                java.lang.String.format("Hello %s, %s", name, greetingConfig[EVENING_MESSAGE])
            }
            else -> {
                java.lang.String.format("Hello %s, %s", name, greetingConfig[NIGHT_MESSAGE])
            }
        }
    }
}
