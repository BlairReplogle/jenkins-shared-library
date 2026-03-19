package org.blorg

class Ubuntu implements Serializable {
	private def script

	Ubuntu(script) {
		this.script = script
	}

	def check() {
		def path = env.PATH

		def usrLocalBin = "/usr/local/bin"
		if (!path.contains(usrLocalBin)) {
			script.error "Path does not contain ${usrLocalBin}! Path: ${path}"
		}

		def lang = "en_US.UTF-8"
		if (env.LANG != lang) {
			script.error "LANG environment variable not set to ${lang}"
		}

		def sandbox = "/opt/chromium-sandbox/chrome-sandbox"
		if (env.CHROME_DEVEL_SANDBOX != sandbox) {
			script.error "CHROME_DEVEL_SANDBOX environment variable not set to ${sandbox}"
		}

		script.echo "✓ Ubuntu setup correctly"
	}
}
