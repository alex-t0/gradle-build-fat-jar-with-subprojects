package my.examples.scriptengine

import org.junit.Assert
import org.junit.Test
import javax.script.Compilable
import javax.script.ScriptEngineManager

class KotlinScriptEngineTest {
    @Test
    fun startKotlinScriptEngineTest(){
        val kotlinEngine = ScriptEngineManager().getEngineByName("kotlin")
        // val kotlinEngine = ScriptEngineManager().getEngineByExtension("kts")
        Assert.assertNotNull(kotlinEngine)

        val script = "3 + 5";

        var result: Any = kotlinEngine.eval(script)

        Assert.assertEquals(8, result);
    }

    @Test
    fun compilableKotlinEngineTest(){
        val compilableEngine = ScriptEngineManager().getEngineByName("kotlin") as Compilable
        Assert.assertNotNull(compilableEngine)
        var source = "3 + 5"
        val compiled = compilableEngine.compile(source)
        val result = compiled.eval()

        Assert.assertEquals(8, result);
    }
}