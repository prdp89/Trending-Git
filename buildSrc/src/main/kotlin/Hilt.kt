object Hilt {
    //const val hiltVersion = "2.38.1"
    const val hiltVersion = "2.37"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"

    const val dagger = "com.google.dagger:dagger:$hiltVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$hiltVersion"

    const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
}

object HiltTest {
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
}