//
// checkUbuntu.groovy
// Checks to make sure environment is setup correctly on `ubuntu-latest` executor
//

import org.blorg.Ubuntu

def call() {
	def ubuntu = new Ubuntu(this)
	ubuntu.check()

	return true
}
