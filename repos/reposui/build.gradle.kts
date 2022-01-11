import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addHiltNavigationDependency
import com.example.gitapp.build.dependencies.addThirdPartyUiDependencies

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
    implementation(project(":git-domain:base"))
    implementation(project(":components"))
    api(project(":repos:reposinteractors"))
    api(project(":core"))
    implementation(project(mapOf("path" to ":coroutines:dispatchers")))
    api(project(":paging"))
    addThirdPartyUiDependencies()
    implementation(project(":navigator"))
}