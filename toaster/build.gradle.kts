import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency

plugins {
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addComposeOfficialDependencies()
    addHiltDependency()
}