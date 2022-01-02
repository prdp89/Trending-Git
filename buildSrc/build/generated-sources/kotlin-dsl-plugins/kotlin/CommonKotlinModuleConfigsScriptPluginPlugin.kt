/**
 * Precompiled [common-kotlin-module-configs-script-plugin.gradle.kts][Common_kotlin_module_configs_script_plugin_gradle] script plugin.
 *
 * @see Common_kotlin_module_configs_script_plugin_gradle
 */
class CommonKotlinModuleConfigsScriptPluginPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Common_kotlin_module_configs_script_plugin_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
