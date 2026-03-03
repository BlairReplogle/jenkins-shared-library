//
// asdfInstall.groovy
// Runs 'asdf install' for the repository
//

def call(Map config = [:]) {
	// Set default values
	def setup = config.setup ?: false

	if (setup) {
		setup_asdf()
	}

	sh '''
		# Verify asdf is available
		which asdf || (echo "asdf not found in PATH" && exit 1)

		# Run asdf install to install all tools from .tool-versions
		asdf install
	'''
}
