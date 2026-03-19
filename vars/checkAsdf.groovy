//
// checkAsdf.groovy
// Checks to make sure the `asdf` command is available
//

import org.blorg.Asdf

def call(Map options = [:]) {
	def asdf = new Asdf(this)
	asdf.check()

	if (options.containsKey('plugins') && (options.plugins instanceof List)) {
		options.plugins.each { plugin ->
			checkAsdfPlugin(plugin)
		}
	}

	return true
}
