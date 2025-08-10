plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.guppy.guppyscreen"
    compileSdk = 35
    ndkVersion = "28.0.13004108"

    defaultConfig {
        applicationId = "com.guppy.guppyscreen"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
//                arguments += "-DGUPPY_SMALL_SCREEN=1"
//                cppFlags += "-std=c++17 -DGUPPY_SMALL_SCREEN"
//                cFlags += "-DGUPPY_SMALL_SCREEN"
                
//                arguments += "-DSIMULATOR=1"
//                cFlags += "-DSIMULATOR"
//                cppFlags += "-std=c++17 -DSIMULATOR"
            }
        }
    }

    signingConfigs{
        // create("release") {
        //     storeFile = file(System.getenv("KEYSTORE"))
        //     storePassword = System.getenv("KEYSTORE_PASSWORD")
        //     keyAlias = System.getenv("KEY_ALIAS")
        //     keyPassword = System.getenv("KEY_PASSWORD")
        // }
    }

    buildTypes {
        getByName("release") {
            // signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
}
