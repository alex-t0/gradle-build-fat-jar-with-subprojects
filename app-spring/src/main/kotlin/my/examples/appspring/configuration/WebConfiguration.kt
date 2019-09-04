package my.examples.appspring.configuration

import nz.net.ultraq.thymeleaf.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import my.examples.appspring.mvc.KotlinViewResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.dom4j.dom.DOMNodeHelper.setPrefix
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext


@Configuration
class WebConfiguration : WebMvcConfigurer {
    /*@Bean
    fun kotlinScriptConfigurer() = KotlinTemplateConfigurer().apply {
        engineName = "kotlin"
        setScripts("scripts/render.kts")
        renderFunction = "render"
        isSharedEngine = false
    }*/

    /*@Bean
    fun kotlinScriptViewResolver() = KotlinTemplateViewResolver().apply {
        setPrefix("templates/")
        setSuffix(".html.kts")
    }*/

   /* @Bean
    fun kotlintViewResolver() = KotlinViewResolver().apply {

    }*/

    @Autowired
    private val applicationContext: ApplicationContext? = null

    @Bean
    fun viewResolver(): ThymeleafViewResolver {
        val viewResolver = ThymeleafViewResolver()
        viewResolver.templateEngine = templateEngine()
        // NOTE 'order' and 'viewNames' are optional
        viewResolver.order = 1
        viewResolver.viewNames = arrayOf(".html", ".xhtml")
        return viewResolver
    }

    @Bean
    fun templateResolver(): SpringResourceTemplateResolver {
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.setApplicationContext(this.applicationContext!!) // throw if none
        templateResolver.prefix = "classpath:thymeleaf/"
        templateResolver.suffix = ".html"
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.templateMode = TemplateMode.HTML
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.isCacheable = true

        templateResolver.order = 0

        return templateResolver
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine.addDialect(LayoutDialect())
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.enableSpringELCompiler = true
        return templateEngine
    }
}