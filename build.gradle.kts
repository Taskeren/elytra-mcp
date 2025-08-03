plugins {
	`maven-publish`
	idea
}

// publishing under a well-known group by default, so that facilities that has convention on this can use it easily.
group = System.getenv("GROUP") ?: "de.oceanlabs.mcp"
// VERSION needs to be injected while publishing.
version = System.getenv("VERSION") ?: "999"

val channel: String = "stable"

tasks.register("mcpPack", Zip::class) {
	group = "mcp"
	description = "Packs the MCP folder to a ZIP file."

	from(file("mcp"))

	archiveBaseName = "mcp_${channel}"
	archiveVersion = project.version.toString()

	archiveFileName = provider { "${archiveBaseName.get()}_${archiveVersion.get()}.zip" }
	destinationDirectory = layout.buildDirectory
}

publishing {
	publications {
		create<MavenPublication>("mcpPackZip") {
			artifact(tasks.named("mcpPack"))
			groupId = project.group.toString()
			artifactId = "mcp_${channel}"
			version = project.version.toString()
		}
	}
}

idea {
	module {
		// make the mcp folder blue~
		sourceDirs.add(file("mcp"))
	}
}
