package org.blorg

class Android implements Serializable {
	private def script
	private List<String> outputs = []

	Android(script) {
		this.script = script
	}

	def check() {
		// Check if ANDROID_HOME is set
		androidHome = script.env.ANDROID_HOME
		if (!androidHome) {
			script.error("ANDROID_HOME environment variable is not set")
		}

		outputs.add("✓ ANDROID_HOME is set: ${androidHome}")

		// Check if PATH includes cmdline-tools
		if (!script.env.PATH.contains("${androidHome}/cmdline-tools")) {
			script.error("PATH does not include ${androidHome}/cmdline-tools")
		}

		outputs.add("✓ PATH includes cmdline-tools")

		// Check if PATH includes platform-tools
		if (!script.env.PATH.contains("${androidHome}/platform-tools")) {
			script.error("PATH does not include ${androidHome}/platform-tools")
		}

		outputs.add("✓ PATH includes platform-tools")

		script.echo(outputs.join("\n"))
		return true
	}
}
