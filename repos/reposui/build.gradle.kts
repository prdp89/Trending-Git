import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addHiltNavigationDependency

plugins {

    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    addComposeOfficialDependencies()
    addHiltDependency()
    addHiltNavigationDependency()
    implementation(project(":style"))
    implementation(project(":git-domain:trending"))
    implementation(project(":components"))
    api(project(":repos:reposinteractors"))
}