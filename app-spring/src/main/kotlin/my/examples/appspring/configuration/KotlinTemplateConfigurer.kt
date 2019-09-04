package my.examples.appspring.configuration

import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
import javax.script.ScriptEngine

class KotlinTemplateConfigurer : ScriptTemplateConfigurer(){
    override fun getScripts(): Array<String>? {
        val scripts = super.getScripts()
        return scripts
    }

    override fun getRenderFunction(): String? {
        val renderFunction = super.getRenderFunction()
        return renderFunction
    }

    override fun getEngine(): ScriptEngine? {
        val engine = super.getEngine()
        return engine
    }

    override fun getResourceLoaderPath(): String? {
        val resourceLoaderPath = super.getResourceLoaderPath()
        return resourceLoaderPath
    }
}