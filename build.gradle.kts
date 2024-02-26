// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.hilt.plugin) apply false
}

subprojects {
    afterEvaluate {
        if (hasProperty("android")) {
            val androidExtension =
                extensions.findByName("android") as? com.android.build.gradle.BaseExtension
            androidExtension?.apply {
                if (namespace == null) {
                    namespace = project.group.toString()
                }
                compileSdkVersion(34)
                buildToolsVersion("30.0.3")

                defaultConfig {
                    minSdk = 34
                    targetSdk = 34
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }

                viewBinding {
                    enable = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                    kotlinOptions {
                        jvmTarget = "1.8" // or whichever version you prefer
                    }
                }
            }
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
