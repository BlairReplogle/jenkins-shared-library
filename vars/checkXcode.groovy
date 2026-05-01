//
// checkXcode.groovy
// Checks to make sure xcodes, command-line tools and xcode versions are installed
//

import org.blorg.Xcode

def call() {
	def xcode = new Xcode(this)
	xcode.check()

	return true
}
