// vars/checkAsdfPlugin.groovy

import org.blorg.Asdf

def call(String pluginName) {
	if (!pluginName) {
		error("Plugin name cannot be empty")
	}

	def asdf = Asdf(this)
	asdf.checkPlugin(pluginName)
}
