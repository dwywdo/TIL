package me.dwywdo.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "dwywdo.greeter")
data class GreeterProperties(
   val userName: String,
   val morningMessage: String,
   val afternoonMessage: String,
   val eveningMessage: String,
   val nightMessage: String
)
