package me.dwywdo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("app")
class AppInfo {

    lateinit var name: String

    lateinit var description: String

    lateinit var url: String

    var user: UserInfo = UserInfo()

    companion object {
        class UserInfo {
            lateinit var username: String
            lateinit var password: String
            lateinit var roles: Set<String>

            override fun toString(): String {
                return "UserInfo(username='$username', password='$password', roles=$roles)"
            }
        }
    }

    override fun toString(): String {
        return "AppInfo(name='$name', description='$description', url='$url', user=$user)"
    }
}
