plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-validator:commons-validator:1.7")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("org.json:json:20171018")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.1")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}