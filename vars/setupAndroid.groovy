//
// setupAndroid.groovy
// Sets up android environment variables
//

import org.blorg.Android

def call() {
	def android = new Android(this)
	android.setup()

	return true
}
