package my.examples.appspring.support

import my.examples.appdspring.support.ExampleKotlinScriptEngine
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineBase
import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineFactory

/**
 * Configures a [ScriptEngineFactory] that produces a JSR-223 [ScriptEngine] that is configured with entries from the
 * Spring Boot fat jar.
 */
internal class ExampleKotlinScriptEngineFactory : KotlinJsr223JvmScriptEngineFactoryBase() {

    override fun getScriptEngine(): KotlinJsr223JvmScriptEngineBase {
        return ExampleKotlinScriptEngine(
                this,
                springBootClassPath,
                KotlinStandardJsr223ScriptTemplate::class.qualifiedName!!,
                { ctx, types ->
                    ScriptArgsWithTypes(arrayOf(ctx.getBindings(ScriptContext.ENGINE_SCOPE)), types ?: emptyArray())
                },
                arrayOf(Bindings::class)
        )
    }
}