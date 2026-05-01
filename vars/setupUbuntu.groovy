//
// setupHomebrew.groovy
// Sets up homebrew
//

import org.blorg.Ubuntu

def call() {
	def ubuntu = new Ubuntu(this)
	ubuntu.setup()

	return true
}
