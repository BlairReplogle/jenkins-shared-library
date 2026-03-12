//
// setupHomebrew.groovy
// Sets up homebrew
//

def call(Map config = [:]) {
	println("Setting up Homebrew...")

	def homebrewPrefix = env.HOMEBREW_PREFIX ?: "/opt/homebrew"
	def homebrewPrefixBin = "${homebrewPrefix}/bin"
	def homebrewPrefixSbin = "${homebrewPrefix}/sbin"

	// Set auld LANG syne - put this somewhere else eventually... probably
	env.LANG = "en_US.UTF-8"

	// Set environment variable that persists across sh steps
	env.PATH = "${homebrewPrefixBin}:${homebrewPrefixSbin}:${env.PATH}"

	// Check paths were added to the path
	if ( !env.PATH.contains(homebrewPrefixBin) || !env.PATH.contains(homebrewPrefixSbin) ) {
		error('Failed to configure environment for Homebrew')
	}
}
