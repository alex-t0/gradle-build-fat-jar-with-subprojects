package my.examples.appspring.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class KotlinController {
    @GetMapping("/kotlin")
    fun hello(model: Model): String {
        return "hello"
    }
}