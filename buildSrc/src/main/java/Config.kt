object Version {
    const val kotlinVersion = "1.4.30"
    const val navigationVersion = "2.3.5"
    const val daggerVersion = "2.28.3"
    const val retrofitVersion = "2.9.0"
    const val glideVersion = "4.11.0"
}

object Lib {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
    val androidxCoreKtx = "androidx.core:core-ktx:1.6.0"
    val androidxAppCompat = "androidx.appcompat:appcompat:1.3.1"
    val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"

    //Material components
    val material = "com.google.android.material:material:1.4.0"

    //Navigation
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigationVersion}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigationVersion}"

    //Dagger 2
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version.daggerVersion}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:2.29.1"

    //Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofitVersion}"

    //RxJava
    val rxJava = "io.reactivex.rxjava2:rxjava:2.2.21"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"

    //Glide
    val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glideVersion}"
}

object TestLib {
    val junit = "junit:junit:4.+"

    val androidxTestJunit = "androidx.test.ext:junit:1.1.3"

    val androidxTestEspresso = "androidx.test.espresso:espresso-core:3.4.0"
}
