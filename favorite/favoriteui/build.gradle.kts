import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltNavigationDependency
import com.example.gitapp.build.dependencies.addThirdPartyUiDependencies

plugins {

    id("common-compose-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    //addCoreAndroidDependencies()
    addComposeOfficialDependencies()
    addHiltNavigationDependency()
    implementation(project(":favorite:favoritedb"))
    api(project(":paging"))
    implementation(project(":components"))
    implementation(project(":core"))
    implementation(project(":style"))
    implementation(project(":git-domain:base"))
    implementation("com.google.accompanist:accompanist-insets:0.15.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.15.0")
    addThirdPartyUiDependencies()
}