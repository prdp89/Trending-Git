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

    implementation(project(":navigator"))
    api(project(":paging"))

    api(project(":repodetails:repodetailinteractor"))
    implementation(project(":git-domain:trending"))
    implementation(project(":git-domain:base"))
    api(project(":core"))
    addThirdPartyUiDependencies()
    implementation(project(":style"))

    api(project(":favorite:favoritedb"))
    implementation(project(":components"))
    implementation("androidx.compose.ui:ui-util:1.0.5")
    implementation("com.google.accompanist:accompanist-insets:0.15.0")
}