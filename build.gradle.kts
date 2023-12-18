plugins {
  id("org.jetbrains.kotlin.multiplatform").version("1.9.20")
}

kotlin {
  js(IR) {
    nodejs()
  }
  
  sourceSets {
    getByName("jsTest") {
      dependencies {
        implementation(kotlin("test"))
        implementation("io.ktor:ktor-client-core:2.3.7")
        implementation("io.ktor:ktor-client-js:2.3.7")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC")
        implementation("com.apollographql.apollo3:apollo-mockserver:4.0.0-beta.4")
      }
    }
  }
}