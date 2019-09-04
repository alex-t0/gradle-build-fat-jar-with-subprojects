buildscript {
     dependencies {
        // classpath 'com.moowork.gradle:gradle-node-plugin:1.2.0'
         classpath("com.moowork.gradle:gradle-node-plugin:1.3.1")
    }
}

plugins {
    id("base")
    id("com.moowork.node") version "1.3.1"
}

node {
    version = "10.16.3"
    npmVersion = "6.9.0"
    download = true
}

val npm_run_build = tasks.getByName("npm_run_build") {

    if (this == null) throw Exception("npm_run_build is null")

    inputs.files(fileTree("public"))
    inputs.files(fileTree("src"))

    inputs.file("package.json")
    inputs.file("package-lock.json")

    outputs.dir("build")

    println("npm_run_build")
}

val packageNpmApp by tasks.registering(Zip::class) {
    dependsOn(npm_run_build)
    archiveBaseName.set("app-react-components")
    archiveExtension.set("jar")
    destinationDirectory.set(File("$projectDir/build_packageNpmApp"))
    from("build")
    into("static")
}

val npmResources by configurations.creating

configurations {
    // npmResources
    default.get().extendsFrom(npmResources)
}

artifacts {
    add("archives", packageNpmApp) {
        type = "jar"
        builtBy(packageNpmApp)
    }
}

tasks.withType<Assemble>(){
    dependsOn(packageNpmApp)
}

tasks.registering(Delete::class){
    println("deleting " + packageNpmApp.get().destinationDirectory)
    delete(packageNpmApp.get().destinationDirectory)
}

