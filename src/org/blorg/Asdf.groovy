package org.blorg

class Asdf implements Serializable {
	private static String cachedPluginList = null
	private def script

	Asdf(script) {
		this.script = script
	}

	def check() {
		def version = script.sh(
			script: 'asdf --version 2>/dev/null',
			returnStdout: true
		).trim()

		script.echo "✓ asdf is installed: ${version}"
	}

	def checkPlugin(String pluginName) {
		if (!pluginName) {
			script.error("Plugin name cannot be empty")
		}

		def pluginList = Asdf.pluginList(script)

		if (pluginList.contains(pluginName)) {
			script.echo "✓ asdf plugin '${pluginName}' is installed"
			return true
		} else {
			script.error("asdf plugin '${pluginName}' is not installed. Available plugins:\n${pluginList}")
		}
	}

	static String pluginList(script) {
		if (cachedPluginList == null) {
			cachedPluginList = script.sh(
				script: 'asdf plugin list 2>/dev/null',
				returnStdout: true
			).trim()
		}
		return cachedPluginList
	}

	static void clearCache() {
		Asdf.cachedPluginList = null
	}
}
