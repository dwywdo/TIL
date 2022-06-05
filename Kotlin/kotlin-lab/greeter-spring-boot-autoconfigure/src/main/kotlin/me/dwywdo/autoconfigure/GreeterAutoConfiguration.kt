package me.dwywdo.autoconfigure

import me.dwywdo.greeter.library.Greeter
import me.dwywdo.greeter.library.GreeterConfigParams.AFTERNOON_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.EVENING_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.MORNING_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.NIGHT_MESSAGE
import me.dwywdo.greeter.library.GreeterConfigParams.USER_NAME
import me.dwywdo.greeter.library.GreetingConfig
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * For why this configuration class is made 'open' in kotlin,
 * Please refer
 * - https://blog.frankel.ch/open-your-classes-and-methods-in-kotlin/
 */
@Configuration
@ConditionalOnClass(Greeter::class)
@EnableConfigurationProperties(GreeterProperties::class)
open class GreeterAutoConfiguration(
    private val greeterProperties: GreeterProperties
) {
    @Bean
    @ConditionalOnMissingBean
    open fun greeterConfig(): GreetingConfig {
        val userName = greeterProperties.userName
        val morningMessage = greeterProperties.morningMessage
        val afternoonMessage = greeterProperties.afternoonMessage
        val eveningMessage = greeterProperties.eveningMessage
        val nightMessage = greeterProperties.nightMessage

        val greetingConfig = GreetingConfig()
        greetingConfig.put(USER_NAME, userName)
        greetingConfig.put(MORNING_MESSAGE, morningMessage)
        greetingConfig[AFTERNOON_MESSAGE] = afternoonMessage
        greetingConfig[EVENING_MESSAGE] = eveningMessage
        greetingConfig[NIGHT_MESSAGE] = nightMessage

        return greetingConfig
    }

    @Bean
    @ConditionalOnMissingBean
    open fun greeter(greetingConfig: GreetingConfig): Greeter {
        return Greeter(greetingConfig)
    }
}
