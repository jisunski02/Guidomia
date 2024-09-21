plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlinksp)
    alias(libs.plugins.kotlinkapt)
    alias(libs.plugins.navSafeArgs)
}

android {
    namespace = "dev.jaysonguillen.guidomia"
    compileSdk = 34


    buildFeatures{
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "dev.jaysonguillen.guidomia"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.kotlinx.core)
    implementation (libs.kotlinx.android)
    implementation (libs.google.gson)
    implementation (libs.squareup.retrofit)
    implementation (libs.squareup.gson.converter)
    implementation (libs.squareup.okhttp3)
    implementation (libs.androidx.viewmodel)
    implementation (libs.androidx.livedata)
    implementation (libs.androidx.fragment)
    implementation (libs.androidx.ui)
    implementation (libs.bumptech.glide)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation (libs.google.dagger.hilt)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.ui)
    //implementation libs.google.dagger
//    implementation libs.google.dagger.android.support

    implementation(platform(libs.org.jetbrains.kotlin))

    testImplementation (libs.junit)
    testImplementation (libs.junit.jupiter)
    testImplementation (libs.io.mockk)
    testImplementation (libs.androidx.core.ktx)
    testImplementation (libs.androidx.arch.core)
    testImplementation (libs.kotlinx.test)


    ksp(libs.androidx.compiler)
    ksp(libs.androidx.room.compiler)
    //ksp(libs.google.dagger.compiler)
    ksp(libs.google.dagger.hilt.compiler)
//    ksp(libs.google.dagger.android.processor)
    ksp(libs.bumptech.compiler)
}