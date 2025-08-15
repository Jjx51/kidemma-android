// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

detekt {
    config = files("$rootDir/detekt.yml") // Archivo de configuración opcional
    buildUponDefaultConfig = true // Usa la configuración por defecto y añade la tuya
    allRules = false // Activa todas las reglas (opcional)
    autoCorrect = true // Corrige automáticamente los problemas (opcional)
}