plugins {
	`maven-publish`
	idea
}

// publishing under a well-known group by default, so that facilities that has convention on this can use it easily.
group = System.getenv("GROUP") ?: "de.oceanlabs.mcp"
// VERSION needs to be injected while publishing.
version = System.getenv("VERSION") ?: "999"

val channel: String = "stable"

val mcpPackTask = tasks.register("mcpPack", Zip::class) {
	group = "mcp"
	description = "Packs the MCP folder to a ZIP file."

	from(file("mcp"))

	archiveBaseName = "mcp_${channel}"
	archiveVersion = project.version.toString()

	archiveFileName = provider { "${archiveBaseName.get()}_${archiveVersion.get()}.zip" }
	destinationDirectory = layout.buildDirectory
}

tasks.register("assemble") { // required for Jitpack
	group = "build"
	dependsOn(mcpPackTask)
}

publishing {
	publications {
		create<MavenPublication>("mcpPackZip") {
			artifact(mcpPackTask)
			groupId = project.group.toString()
			artifactId = "mcp_${channel}"
			version = project.version.toString()
		}
	}
	repositories {
		maven {
			name = "local"
			url = uri("${project.rootDir}/publishing")
		}
	}
}

tasks.withType<PublishToMavenRepository>().configureEach {
	doFirst {
		if(project.version.toString() == "999") {
			throw GradleException("Publishing in an invalid version ${project.version}.")
		}
	}
}

idea {
	module {
		// make the mcp folder blue~
		sourceDirs.add(file("mcp"))
	}
}
