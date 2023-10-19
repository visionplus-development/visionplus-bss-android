val FACEBOOK_APP_ID: String by project
val FB_LOGIN_PROTOCOL_SCHEME: String by project
val FACEBOOK_CLIENT_TOKEN: String by project
val GOOGLE_SIGN_TOKEN: String by project

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.zte.iptvclient.android.auth"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }

        resValue("string", "FACEBOOK_APP_ID", FACEBOOK_APP_ID)
        resValue("string", "FACEBOOK_CLIENT_TOKEN", FACEBOOK_CLIENT_TOKEN)
        resValue("string", "FB_LOGIN_PROTOCOL_SCHEME", FB_LOGIN_PROTOCOL_SCHEME)
        buildConfigField("String", "GOOGLE_SIGN_TOKEN", "\"${GOOGLE_SIGN_TOKEN}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.3")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.facebook.android:facebook-android-sdk:15.1.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "id.visionplus"
            artifactId = "visionplus-bss-android"
            version = "0.0.2"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}