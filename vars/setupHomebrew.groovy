//
// setupHomebrew.groovy
// Sets up homebrew
//

import org.blorg.Homebrew

def call(Map config = [:]) {
	def homebrew = new Homebrew(this)
	homebrew.setup()

	return true
}
