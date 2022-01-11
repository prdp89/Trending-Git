import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addDataDependencies
import com.example.gitapp.build.dependencies.addHiltDependency

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addComposeOfficialDependencies()
    addHiltDependency()
    addDataDependencies()
    implementation(project(":core"))
    implementation(project(":git-domain:base"))
    implementation(project(mapOf("path" to ":coroutines:dispatchers")))
    api(project(":paging"))
}