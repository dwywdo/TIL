package me.dwywdo.controller

import me.dwywdo.configuration.AppInfo
import me.dwywdo.greeter.library.Greeter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @Value("\${say-hello: default hello message}")
    lateinit var sayHello: String

    @Value("\${color-list}")
    lateinit var colorList: List<String>

    @Value("#{\${configs}}")
    lateinit var configs: Map<String, String>

    @Autowired
    lateinit var appInfo: AppInfo

    @Autowired
    lateinit var greeter: Greeter

    @GetMapping("/hello")
    fun hello(): String {
        return "say : $sayHello"
    }

    @GetMapping("/colors")
    fun colors(): String {
        return colorList.toString()
    }

    @GetMapping("/configs")
    fun configs(): String {
        return configs.toString()
    }

    @GetMapping("/appInfo")
    fun appInfo(): String {
        return appInfo.toString()
    }

    @GetMapping("/greeting")
    fun greeting(): String {
        return greeter.greet()
    }
}
