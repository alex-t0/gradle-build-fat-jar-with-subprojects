package my.examples.appspring.mvc

import org.springframework.core.Ordered
import org.springframework.web.servlet.View
import org.springframework.web.servlet.ViewResolver
import java.util.*

// Not used for now
class KotlinViewResolver(): ViewResolver, Ordered {
    var resolverOrder: Int = 0

    override fun getOrder(): Int {
        return resolverOrder;
    }

    fun setOrder(newOrder: Int){
        resolverOrder = newOrder;
    }

    override fun resolveViewName(viewName: String, locale: Locale): View? {
        return KotlinView("templates/kotlin/hello.html.kts");
    }
}