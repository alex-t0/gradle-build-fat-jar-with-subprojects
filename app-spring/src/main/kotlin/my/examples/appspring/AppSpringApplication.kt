package my.examples.appspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class AppSpringApplication

fun main(args: Array<String>) {
	runApplication<AppSpringApplication>(*args)
}
