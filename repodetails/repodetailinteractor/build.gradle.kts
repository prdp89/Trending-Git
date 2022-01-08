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
    addHiltDependency()
    //implementation(project(":repos:reposinteractors"))
    implementation(project(":core"))
}