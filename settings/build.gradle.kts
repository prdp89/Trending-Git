import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addHiltNavigationDependency

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    //id("common-kotlin-module-configs-script-plugin")
    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    //addCoreAndroidDependencies()
    addComposeOfficialDependencies()
    addHiltDependency()
    addHiltNavigationDependency()

    implementation("com.google.accompanist:accompanist-insets:0.15.0")

    //data store
    api("androidx.datastore:datastore-preferences:1.0.0-rc02")
}