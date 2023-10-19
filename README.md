# Android BSS SDK

## Setup
### Step 1. Add the JitPack repository to your build file
Add it in your build.gradle (root) at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2. Add the dependency
Add it in your build.gradle (app):
```groovy
dependencies {
    implementation 'com.github.visionplus-development:visionplus-bss-android:$latest_version'
}
```

### Step 3. Usage
#### Config
```kotlin
// init sdk on .Application
VPAuth.init(this) // required

// set language preferences: (en/id)
VPAuth.updateLanguage("en")
```

#### Login
```kotlin
// open login page
val activityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    when(it.resultCode) {
        Result.SUCCESS -> {
            val token = it.data?.extras?.getString("token")
        }
        Result.CANCELLED -> {
        }
        Result.FAILED -> {
        }
    }
}
VPAuth.login(this, activityForResult)
```

#### Logout
```kotlin
// logout google
VPAuth.logoutGoogle(this)

// logout facebook
VPAuth.logoutFacebook()
```
