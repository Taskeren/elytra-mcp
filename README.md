# Elytra MCP

Nothing more than packing the latest FML MCP mappings and publish them to Maven repositories.

## LICENSE

This project is licensed under MIT except the MCP data, which is obtained
from [MinecraftForge/FML](https://github.com/MinecraftForge/FML).

#### MCP Data License to FML

```
=== MCP Data ===
This software includes data from the Minecraft Coder Pack (MCP), with kind permission
from them. The license to MCP data is not transitive - distribution of this data by
third parties requires independent licensing from the MCP team. This data is not
redistributable without permission from the MCP team.
```

But because MCP project has been dead since around MC 1.15, so I took them without their permission.

**USE AT YOUR OWN RISKS.**

## Using

```kotlin
repositories {
	// ...
    maven {
        name = "Elytra MCP"
        url = uri("https://raw.githubusercontent.com/Taskeren/elytra-mcp/master/publishing/")
    }
}

dependencies {
	// technically, you can access the published zips in this way,
	// but normally, you're not going to deal with this.
	implementation("de.oceanlabs.mcp:mcp_stable:{VERSION}@zip")
}
```

#### Unimined Example

```kotlin
unimined.minecraft {
	// ...
	mappings {
		// use Elytra MCP version 1.0.0-elytra.
		// don't forget to add the repository!
		mcp("stable", "1.0.0-elytra")
    }
}
```

## Publishing

Execute the command below to publish it.

```bat
.\publish.bat <VERSION_TO_PUBLISH>
```
