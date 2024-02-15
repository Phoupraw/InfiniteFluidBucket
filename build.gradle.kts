plugins {
    kotlin("jvm")
    id("fabric-loom")
    `maven-publish`
}
version = property("mod_version")!!
group = property("maven_group")!!
val minecraftVersion = property("minecraft_version")!!
val baseName = property("archives_base_name").toString()

repositories {
    mavenLocal {
        content {
            includeGroup("phoupraw.mcmod")
        }
    }
    maven("https://api.modrinth.com/maven") {
        name = "Modrinth"
        content {
            includeGroup("maven.modrinth")
        }
    }
    maven("https://minecraft.curseforge.com/api/maven/") {
        name = "CurseForge"
        content {
            includeGroup("curse.maven")
        }
    }
    maven("https://maven.terraformersmc.com/") {
        name = "TerraformersMC"
        content {
            includeGroup("com.terraformersmc") //modmenu
            includeGroup("dev.emi")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modApi("net.fabricmc:fabric-loader:${property("loader_version")}")
    modApi("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
    modLocalRuntime("com.terraformersmc:modmenu:${property("modmenu")}")
    include(modImplementation("phoupraw.mcmod:PhouprawsLinkedLib:+")!!)
    //由于KT自带的与java互操作不太好用，所以我自己写了一个
    modCompileOnlyApi("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}") {
        exclude(module = "fabric-transfer-api-v1")
    }
    modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
    modCompileOnly("phoupraw.mcmod:FabricAPIKotlinOverwrite:+")
}

tasks {
    processResources {
        inputs.property("version", version)
        filesMatching("fabric.mod.json") {
            expand("version" to version)
        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "17"
        kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
        kotlinOptions.freeCompilerArgs += "-Xextended-compiler-checks"
        kotlinOptions.freeCompilerArgs += "-Xlambdas=indy"
        kotlinOptions.freeCompilerArgs += "-Xjdk-release=17"
    }
    compileJava {
        targetCompatibility = "17"
    }
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
java {
    //    withJavadocJar()
    withSourcesJar()
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = baseName
            version = version
            println(getComponents().toList())
            from(getComponents()["java"])
        }
    }
}
fabricApi {
    configureDataGeneration()
}
kotlin {
    jvmToolchain(17)
}