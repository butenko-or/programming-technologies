plugins {
    id("application")
    id("checkstyle")
//    id("pmd")
}

group = "or.butenko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:6.2.5")
    testImplementation("org.springframework:spring-test:6.2.5")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    // Log
    implementation("ch.qos.logback:logback-classic:1.5.17")

    // Test
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.1")
}

application {
    mainClass = "or.butenko.Main"
}

checkstyle {
    toolVersion = "10.23.0"
    configFile = file("config/checkstyle/checkstyle-personal.xml")
    isIgnoreFailures = true
    maxWarnings = 0
    maxErrors = 0
}

//pmd {
//    toolVersion = "7.10.0"
//    isConsoleOutput = true
//}

tasks.test {
    useJUnitPlatform()
}
