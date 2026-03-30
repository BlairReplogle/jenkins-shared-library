package org.blorg

class Android implements Serializable {
	private def script
	private def androidHome
	private List<String> outputs = []

	Android(script) {
		this.script = script
		this.androidHome = script.env.ANDROID_HOME ?: "${script.env.HOME}/Library/Android/sdk"
	}

	def setup() {
		script.env.ANDROID_HOME = androidHome
		script.env.PATH = "${androidHome}/cmdline-tools/latest/bin:${script.env.PATH}"
		script.env.PATH = "${androidHome}/platform-tools:${script.env.PATH}"
		script.env.PATH = "${androidHome}/emulator:${script.env.PATH}"
	}

	def check() {
		// Check if ANDROID_HOME is set
		def androidHome = script.env.ANDROID_HOME
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
