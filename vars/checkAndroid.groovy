//
// checkAndroid.groovy
// Checks to make sure environment is setup correctly for android build tools
//

import org.blorg.Android

def call() {
	def android = new Android(this)
	android.check()

	return true
}
