plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

group = "com.github.Zhuinden.simple-stack-extensions"
version = "2.3.0"

android {
    compileSdkVersion(33)

    defaultConfig {
        minSdkVersion(1)
        targetSdkVersion(31)

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
    api("com.github.Zhuinden:simple-stack:2.7.0") {
        isTransitive = true
    }
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
    configurations.implementation.get().isCanBeResolved = true
    configurations.api.get().isCanBeResolved = true

    isFailOnError = false
    source = android.sourceSets["main"].java.getSourceFiles()
    classpath += project.files(android.bootClasspath.joinToString(separator = File.pathSeparator))
    classpath += configurations.api
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

afterEvaluate {
    publishing {
        publications {
            register("mavenJava", MavenPublication::class) {
                groupId = "com.github.Zhuinden.simple-stack-extensions"
                artifactId = "services-ktx"
                version = "2.3.0"

                from(components["release"])
                artifact(sourcesJar.get())

                pom.withXml {
                    val dependenciesNode: groovy.util.Node =
                        (asNode().get("dependencies") as groovy.util.NodeList).get(0) as groovy.util.Node
                    val configurationNames = arrayOf("implementation", "api")

                    configurationNames.forEach { configurationName ->
                        configurations[configurationName].allDependencies.forEach {
                            if (it.group != null && it.version != "unspecified") {
                                val dependencyNode = dependenciesNode.appendNode("dependency")
                                dependencyNode.appendNode("groupId", it.group)
                                dependencyNode.appendNode("artifactId", it.name)
                                dependencyNode.appendNode("version", it.version)
                                // dependencyNode.appendNode("scope", configurationName)
                            }
                        }
                    }

                    val dependencyNode = dependenciesNode.appendNode("dependency")
                    dependencyNode.appendNode("groupId", "com.github.Zhuinden")
                    dependencyNode.appendNode("artifactId", "state-bundle")
                    dependencyNode.appendNode("version", "1.4.0")
                }
            }
        }
    }
}