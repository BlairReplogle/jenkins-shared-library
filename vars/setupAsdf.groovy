//
// setupAsdf.groovy
// Sets up asdf - adding the shims directory to the path
//

import org.blorg.Asdf

def call(Map options = [:]) {
	def asdf = new Asdf(this)
	asdf.setup()
}
