import com.example.gitapp.build.dependencies.addComposeOfficialDependencies
import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addKotlinDependencies

plugins {
    id("common-kotlin-module-configs-script-plugin")
    id("kotlin-kapt")
}

dependencies {
    addKotlinDependencies()
    implementation(project(":git-domain:trending"))
    implementation(project(":core"))
    addHiltDependency()

    implementation(project(":internetdetector"))

    addComposeOfficialDependencies()

    api(project(":paging"))

    implementation("com.google.code.gson:gson:2.8.7")
}