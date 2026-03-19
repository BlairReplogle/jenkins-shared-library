package org.blorg

class Homebrew implements Serializable {
	private def script
	private String homebrewPrefix
	private String homebrewPrefixBin

	Homebrew(script) {
		this.script = script
		this.homebrewPrefix = env.HOMEBREW_PREFIX ?: "/opt/homebrew"
		this.homebrewPrefixBin = "${this.homebrewPrefix}/bin"
	}

	def setup() {
		// Set auld LANG syne - put this somewhere else eventually... probably
		env.LANG = "en_US.UTF-8"

		// Set environment variable that persists across sh steps
		script.env.PATH = "${homebrewPrefixBin}:${script.env.PATH}"
	}

	def check() {
		// Check paths were added to the path
		if ( !script.env.PATH.contains(homebrewPrefixBin) ) {
			script.error("Failed to configure environment for Homebrew")
		}

		script.echo("✓ Homebrew setup correctly")

		return true
	}
}
