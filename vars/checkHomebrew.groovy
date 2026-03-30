//
// checkHomebrew.groovy
// Checks to make sure environment is setup correctly for Homebrew
//

import org.blorg.Homebrew

def call() {
	def homebrew = new Homebrew(this)
	homebrew.check()

	return true
}
