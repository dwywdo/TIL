package me.dwywdo.lab.gradle

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

interface CoverageLockInExtension {
    val coverageFile: RegularFileProperty
    val counter: Property<String>
    val goal: Property<Float>
    val onCi: Property<Boolean>
    val internalCurrentCoverage: Property<Float>
}
