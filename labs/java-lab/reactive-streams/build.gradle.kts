plugins {
    id("io.freefair.lombok")
}

dependencies {
    implementation(platform("io.projectreactor:reactor-bom:2023.0.8"))
    implementation("io.projectreactor:reactor-core")
}
