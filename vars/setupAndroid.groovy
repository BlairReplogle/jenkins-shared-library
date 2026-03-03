//
// setupAndroid.groovy
// Sets up android environment variables
//

def call(Map config = [:]) {
	def androidHome = env.ANDROID_HOME ?: "${env.HOME}/Library/Android/sdk"

	env.ANDROID_HOME = androidHome
	env.PATH = "${androidHome}/cmdline-tools/latest/bin:${env.PATH}"
	env.PATH = "${androidHome}/platform-tools:${env.PATH}"
	env.PATH = "${androidHome}/emulator:${env.PATH}"
}
