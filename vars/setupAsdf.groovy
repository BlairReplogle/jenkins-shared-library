//
// setupAsdf.groovy
// Sets up asdf - adding the shims directory to the path
//

def call(Map config = [:]) {
	println("Setting up ASDF...")

	// Determine asdf data directory
	// Use ASDF_DATA_DIR if it exists, otherwise default to $HOME/.asdf
	def asdfDataDir = env.ASDF_DATA_DIR ?: "$HOME/.asdf"
	def asdfDataDirShims = "${asdfDataDir}/shims"

	// Set environment variables that persist across sh steps
	env.PATH = "${asdfDataDirShims}:${env.PATH}"

	// check that we added the paths to env.PATH
	// if this fails something really bad has happened
	if ( !env.PATH.contains(asdfDataDirShims) ) {
		error('Failed to configure environment for ASDF!')
	}
}
