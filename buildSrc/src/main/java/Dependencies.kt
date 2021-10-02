import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.geekbrains.android2.skyengdictionary"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val historyscreen = ":historyscreen"
    const val descriptionscreen = ":descriptionscreen"
}

object Versions {
    //Design
    const val appcompat = "1.4.0-alpha03"
    const val multidex = "1.0.3"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.0"
    const val swiperefreshlayout = "1.1.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.5.1"
    const val coroutinesAndroid = "1.5.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.1"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"
    const val koinCompat = "3.1.2"

    //Images
    const val picasso = "2.71828"
    const val glide = "4.12.0"
    const val glideCompiler = "4.12.0"
    const val coil = "0.11.0"

    //Room
    const val roomRuntime = "2.3.0"
    const val roomCompiler = "2.3.0"
    const val roomKtx = "2.3.0"

    //Test
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koin_compat = "io.insert-koin:koin-android-compat:${Versions.koinCompat}"
}

object Images {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val junit_ext = "androidx.test:runner:${Versions.jUnitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}