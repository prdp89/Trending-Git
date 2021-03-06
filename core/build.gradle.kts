import com.example.gitapp.build.dependencies.addDataDependencies
import com.example.gitapp.build.dependencies.addHiltDependency
import com.example.gitapp.build.dependencies.addKotlinDependencies
import com.example.gitapp.build.dependencies.addNetworkingDependencies

plugins {
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addKotlinDependencies()
    implementation(project(":git-domain:trending"))
    implementation(project(":git-domain:base"))
    addNetworkingDependencies()
    addHiltDependency()
    addDataDependencies()
}