import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltNavigationDependency

plugins {

    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    addComposeOfficialDependencies()
    addHiltNavigationDependency()
    implementation(project(":settings"))
    implementation(project(":style"))
    implementation(project(":git-domain:trending"))
}