import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addKotlinDependencies

plugins {
    /*id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")*/

    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addHiltDependency()
    addKotlinDependencies()
    implementation(project(":coroutines:dispatchers"))
}