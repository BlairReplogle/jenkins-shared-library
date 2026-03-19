// vars/checkAsdfPlugin.groovy

import org.blorg.Asdf

def call(String pluginName) {
	if (!pluginName) {
		error("Plugin name cannot be empty")
	}

	def asdf = new Asdf(this)
	asdf.checkAsdfPlugin(pluginName)
}
