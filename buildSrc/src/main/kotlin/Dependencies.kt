object Dependencies {
    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Kotlin {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.datetime}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    }

    object Ktor {
        object Client {
            const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
            const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
            const val ios = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        }

        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    }

    object SQLDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
        const val iosDriver = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
    }

    object Androidx {
        const val core = "androidx.core:core-ktx:1.9.0"
    }
}
