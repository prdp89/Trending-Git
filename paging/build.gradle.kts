import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addCoreAndroidDependencies
import com.example.gitapp.build.dependencies.addHiltDependency

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    addComposeOfficialDependencies()
    addHiltDependency()
    api(project(":toaster"))
    api(project(":internetdetector"))

}