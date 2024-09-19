import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}
val kiparoBaseApiUrl: String = gradleLocalProperties(rootDir).getProperty("KIPARO_BASE_API_URL")
val kiparoNewsApiPath: String = gradleLocalProperties(rootDir).getProperty("KIPARO_NEWS_API_PATH")

android {
    namespace = "com.kiparo.newsappfeaturedagger"
    compileSdk = libs.versions.compileSdk.get().toInt()


    defaultConfig {
        applicationId = "com.kiparo.newsappfeaturedagger"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        android.buildFeatures.buildConfig = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "KIPARO_BASE_API_URL", "\"${kiparoBaseApiUrl}\"")
            buildConfigField("String", "KIPARO_NEWS_API_PATH", "\"${kiparoNewsApiPath}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":data"))
    implementation(project(":data_api"))
    implementation(project(":domain_models"))
    implementation(project(":features:article_details"))
    implementation(project(":features:article_details_api"))
    implementation(project(":features:news"))
    implementation(project(":features:news_api"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.core.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp3.interceptor)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.facebook.fresco)

    // tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
}