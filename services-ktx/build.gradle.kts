plugins {
    id("com.android.library")
    id("com.github.dcendents.android-maven")
    kotlin("android")
}

group = "com.github.Zhuinden"

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(1)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "2.0.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        this.sourceCompatibility(JavaVersion.VERSION_1_7)
        this.targetCompatibility(JavaVersion.VERSION_1_7)
    }
}

dependencies {
    //implementation(mapOf("dir" to "libs", "include" to listOf("*.jar")))
    api("com.google.code.findbugs:jsr305:3.0.2")
    api("com.github.Zhuinden:simple-stack:2.6.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.assertj:assertj-core:3.16.1")
    testImplementation("org.mockito:mockito-core:3.8.0")
    testImplementation("org.robolectric:robolectric:4.2.1")
    testImplementation("org.apache.maven:maven-ant-tasks:2.1.3")
    androidTestImplementation("junit:junit:4.13.2")
}

// build a jar with source files
val sourcesJar by tasks.registering(Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
}

val javadoc by tasks.registering(Javadoc::class) {
    isFailOnError = false
    source = android.sourceSets["main"].java.getSourceFiles()
    classpath += project.files(android.bootClasspath.joinToString(separator = File.pathSeparator))
    classpath += configurations.compile
}

// build a jar with javadoc
val javadocJar by tasks.registering(Jar::class) {
    dependsOn(javadoc)
    archiveClassifier.set("javadoc")
    from(javadoc.get().destinationDir)
}

artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}
