    import java.util.Properties

    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        alias(libs.plugins.kotlin.serialization)
        alias(libs.plugins.hilt.android)
        alias(libs.plugins.ksp)
    }

    val properties = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }

    android {
        namespace = "org.sopt.and"
        compileSdk = 34

        defaultConfig {
            applicationId = "org.sopt.and"
            minSdk = 28
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            buildConfigField("String", "BASE_SERVER_URL", properties["BASE_SERVER_URL"].toString())
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
        buildFeatures {
            compose = true
            buildConfig = true
        }
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        // orbit
        implementation(libs.bundles.orbit)

        // collections-immutable
        implementation (libs.kotlinx.collections.immutable)

        // DataStore
        implementation(libs.androidx.datastore.preferences)
        implementation(libs.androidx.datastore.preferences.core)

        // Hilt
        implementation(libs.hilt.android)
        ksp(libs.hilt.compiler)
        ksp(libs.hilt.android.compiler)
        implementation(libs.hilt.navigation)

        // Timber
        implementation (libs.timber)

        // Coil
        implementation(libs.coil.compose)

        // navigation
        implementation(libs.bundles.navigation)

        // Kotlin Serialization
        implementation(libs.kotlinx.serialization.json)

        // Coroutines
        implementation(libs.kotlinx.coroutines)

        // Network
        implementation(platform(libs.okhttp.bom))
        implementation(libs.bundles.retrofit)
    }