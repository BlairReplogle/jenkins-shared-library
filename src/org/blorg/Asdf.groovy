package org.blorg

class Asdf implements Serializable {
	private static String cachedPluginList = null
	private def script

	Asdf(script) {
		this.script = script
	}

	def checkAsdf() {
		def version = script.sh(
			script: 'asdf --version',
			returnStdout: true
		).trim()

		echo "✓ asdf is installed: ${version}"
	}

	def checkAsdfPlugin(String pluginName) {
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
				script: 'asdf plugin list',
				returnStdout: true
			).trim()
		}
		return cachedPluginList
	}

	static void clearCache() {
		Asdf.cachedPluginList = null
	}
}
