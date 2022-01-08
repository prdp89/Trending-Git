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
    api(project(":core"))
}