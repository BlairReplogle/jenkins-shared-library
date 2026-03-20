package org.blorg

class Asdf implements Serializable {
	private static String cachedPluginList = null

	private def script
	private def asdfDataDirShims

	Asdf(script) {
		this.script = script

		// Determine asdf data directory
		// Use ASDF_DATA_DIR if it exists, otherwise default to $HOME/.asdf
		def asdfDataDir = script.env.ASDF_DATA_DIR ?: "${script.env.HOME}/.asdf"
		this.asdfDataDirShims = "${asdfDataDir}/shims"
	}

	def setup() {
		// Set environment variables that persist across sh steps
		script.env.PATH = "${asdfDataDirShims}:${script.env.PATH}"
	}

	def check() {
		def version = script.sh(
			script: 'asdf --version 2>/dev/null',
			returnStdout: true
		).trim()

		// check that we added the paths to env.PATH
		// if this fails something really bad has happened
		if ( !script.env.PATH.contains(asdfDataDirShims) ) {
			script.error('Failed to configure environment for ASDF!')
		}

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
