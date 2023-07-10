plugins {
    id("com.android.application") version "8.2.0-alpha03" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("org.jetbrains.kotlin.kapt") version "1.8.10" apply false
}

buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.1")
    }
}