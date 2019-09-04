package my.examples.appspring.mvc

import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import org.springframework.web.servlet.View
import javax.script.Compilable
import javax.script.CompiledScript
import javax.script.ScriptEngineManager
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.core.io.ResourceLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import java.util.stream.Collectors
import java.io.BufferedReader
import javassist.bytecode.ByteArray
import my.examples.appspring.scripting.ScriptEngineFactoryManager
import my.examples.appspring.support.ExampleKotlinScriptEngineFactory
import java.io.InputStreamReader
import java.nio.charset.Charset

// Not used for now
class KotlinView constructor(path: String) : View {
    companion object ViewData {
        var Script: CompiledScript? = null
    }

    private var viewPath: String? = null

    init {
        viewPath = path
    }

    override fun render(model: MutableMap<String, *>?, request: HttpServletRequest, response: HttpServletResponse) {
        val engine = ExampleKotlinScriptEngineFactory().scriptEngine;

        // ScriptEngineFactoryManager().GetScriptEngine("kotlin");

        val classLoader = Thread.currentThread().getContextClassLoader();
        val compilableEngine = ScriptEngineManager(classLoader).getEngineByName("kotlin") as Compilable

        setIdeaIoUseFallback(); // hack to use kotlin script engine in spring boot environment

        var resourceStream = classLoader.getResourceAsStream("classpath:$viewPath")

        // val scriptResource = ClassPathResource("classpath:$viewPath", classLoader)
        // val scriptResource = ClassPathResource("classpath:$viewPath")

        // var script = scriptResource.file.readText()
        val sb = StringBuilder()
        var line: String?
        val br = BufferedReader(InputStreamReader(resourceStream))
        line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close();
        var script = sb.toString();

        if (ViewData.Script == null)
            ViewData.Script = compilableEngine.compile(script);

        response.outputStream.print(Script!!.eval().toString())
        // response.outputStream.println("Hello from kotlin view! 3 + 2 = " + compilableEngine.compile("3 + 2").eval())
    }
}