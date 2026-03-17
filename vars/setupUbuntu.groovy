//
// setupHomebrew.groovy
// Sets up homebrew
//

def call(Map config = [:]) {
	println("Setting up Ubuntu...")

	def usrLocalBin = "/usr/local/bin"
	env.PATH = "${usrLocalBin}:${env.PATH}"

	// Set auld LANG syne - put this somewhere else eventually... probably
	env.LANG = "en_US.UTF-8"
	env.CHROME_DEVEL_SANDBOX = "/opt/chromium-sandbox/chrome-sandbox"

	// Check paths were added to the path
	if ( !env.PATH.contains(usrLocalBin) ) {
		error('Failed to configure environment for Ubuntu')
	}
}
