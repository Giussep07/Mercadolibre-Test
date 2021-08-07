object Version {
    val kotlinVersion = "1.4.30"
    val navigationVersion = "2.3.5"
    val daggerVersion = "2.28.3"
}

object Lib {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
    val androidxCoreKtx = "androidx.core:core-ktx:1.6.0"
    val androidxAppCompat = "androidx.appcompat:appcompat:1.3.1"
    val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
    val material = "com.google.android.material:material:1.4.0"

    //Navigation
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"

    //Dagger 2
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version.daggerVersion}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:2.29.1"
}

object TestLib {
    val junit = "junit:junit:4.+"

    val androidxTestJunit = "androidx.test.ext:junit:1.1.3"

    val androidxTestEspresso = "androidx.test.espresso:espresso-core:3.4.0"
}
