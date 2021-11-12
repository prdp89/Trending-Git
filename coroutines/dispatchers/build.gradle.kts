import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addKotlinDependencies

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addHiltDependency()
    addKotlinDependencies()
}