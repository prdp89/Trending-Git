import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltNavigationDependency

plugins {

    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    //addCoreAndroidDependencies()
    addComposeOfficialDependencies()
    addHiltNavigationDependency()
    implementation(project(":git-domain:trending"))
    implementation(project(":git-domain:base"))
    implementation(project(":style"))
    implementation(project(":components"))
}