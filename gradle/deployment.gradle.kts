val tagName = "${project.property("dockerHostname")}/${project.property("dockerRepositoryName")}:${project.property("version")}"

tasks {
    register<Delete>("deleteDeploymentDir") {
        group = "deployment"
        delete("deployment")
    }

    register<Copy>("copyFatWar") {
        group = "deployment"
        dependsOn(
            "deleteDeploymentDir",
            "bootWar",
        )

        val artifactName = tasks.getByName("bootWar").name
            ?: throw Exception("Artifact not found. Did the build succeed?")

        from("build/libs/")
        file(artifactName)
        rename { _ -> "app.war" }
        into("deployment")
    }

    register<Task>("buildArtifacts") {
        group = "deployment"
        dependsOn(
            "copyFatWar",
        )
    }

    register<Exec>("runDockerBuild") {
        group = "deployment"
        dependsOn(
            "buildArtifacts",
        )
        commandLine(
            "docker",
            "build",
            "--no-cache",
            "-t",
            tagName,
            "."
        )

        doLast {
            "deleteDeploymentDir"
        }
    }

    register<Exec>("pushImage") {
        group = "deployment"
        dependsOn(
            "runDockerBuild"
        )

        commandLine("docker", "push", tagName)
    }
}