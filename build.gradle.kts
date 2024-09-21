// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
   alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.daggerHilt)  apply false
    alias(libs.plugins.kotlinksp) apply false
    alias(libs.plugins.kotlinkapt) apply false
    alias(libs.plugins.navSafeArgs) apply false
}