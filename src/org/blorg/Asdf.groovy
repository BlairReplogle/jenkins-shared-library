package org.blorg

class Asdf implements Serializable {
	private static String cachedPluginList = null
	private def script

	Asdf(script) {
		this.script = script
	}

	def check() {
		def version = script.sh(
			script: 'asdf --version',
			returnStdout: true,
			quiet: true
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
				script: 'asdf plugin list',
				returnStdout: true,
				quiet: true
			).trim()
		}
		return cachedPluginList
	}

	static void clearCache() {
		Asdf.cachedPluginList = null
	}
}
