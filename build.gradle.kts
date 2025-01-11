import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask
import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask.JarUrl
import groovy.lang.Closure
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "2.0.21"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("com.palantir.git-version") version "3.1.0"
    id("dev.s7a.gradle.minecraft.server") version "3.2.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jmailen.kotlinter") version "5.0.1"
}

val gitVersion: Closure<String> by extra

val pluginVersion: String by project.ext

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

dependencies {
    shadowImplementation(kotlin("stdlib"))
    compileOnly("org.spigotmc:spigot-api:$pluginVersion-R0.1-SNAPSHOT")
    shadowImplementation(api("com.github.sya-ri:EasySpigotAPI:2.4.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    })
}

configure<BukkitPluginDescription> {
    main = "@group@.Main"
    version = gitVersion()
    apiVersion = "1." + pluginVersion.split(".")[1]
    author = "hirotask"
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation)
    archiveClassifier.set("")
    relocate("kotlin", "@group@.libs.kotlin")
    relocate("org.intellij.lang.annotations", "@group@.libs.org.intellij.lang.annotations")
    relocate("org.jetbrains.annotations", "@group@.libs.org.jetbrains.annotations")
}

tasks.named("build") {
    dependsOn("shadowJar")
}

task<LaunchMinecraftServerTask>("buildAndLaunchServer") {
    dependsOn("build")
    doFirst {
        copy {
            from(buildDir.resolve("libs/${project.name}.jar"))
            into(buildDir.resolve("MinecraftServer/plugins"))
        }
    }

    jarUrl.set(JarUrl.Paper(pluginVersion))
    agreeEula.set(true)
}

task<SetupTask>("setup")
