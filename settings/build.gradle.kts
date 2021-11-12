import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    //addCoreAndroidDependencies()
    addComposeOfficialDependencies()
    addHiltDependency()

    //data store
    api("androidx.datastore:datastore-preferences:1.0.0-rc02")
}