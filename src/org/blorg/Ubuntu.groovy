package org.blorg

class Ubuntu implements Serializable {
	private def script
	private def lang
	private def usrLocalBin
	private def chromeDevelSandbox

	Ubuntu(script) {
		this.script = script
		this.lang = "en_US.UTF-8"
		this.usrLocalBin = "/usr/local/bin"
		this.chromeDevelSandbox = "/opt/chromium-sandbox/chrome-sandbox"
	}

	def setup() {
		script.env.PATH = "${usrLocalBin}:${script.env.PATH}"

		// Set auld LANG syne - put this somewhere else eventually... probably
		script.env.LANG = lang

		// Set CHROME_DEVEL_SANDBOX for puppeteer
		script.env.CHROME_DEVEL_SANDBOX = chromeDevelSandbox
	}

	def check() {
		def path = script.env.PATH

		if (!path.contains(usrLocalBin)) {
			script.error "Path does not contain ${usrLocalBin}! Path: ${path}"
		}

		if (script.env.LANG != lang) {
			script.error "LANG environment variable not set to ${lang}"
		}

		if (script.env.CHROME_DEVEL_SANDBOX != chromeDevelSandbox) {
			script.error "CHROME_DEVEL_SANDBOX environment variable not set to ${chromeDevelSandbox}"
		}

		script.echo "✓ Ubuntu setup correctly"
	}
}
