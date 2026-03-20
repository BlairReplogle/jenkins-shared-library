package org.blorg

class Xcode implements Serializable {
	def script

	Xcode(script) {
		this.script = script
	}

	/**
	 * Checks that Xcode is properly installed and configured on the system
	 * Verifies:
	 * - Command line tools are installed (xcode-select -p and pkgutil)
	 * - xcodes tool is installed
	 * - Each installed Xcode version has licenses agreed to
	 * - Each installed Xcode version has iphoneos SDK available
	 *
	 * @return true if all checks pass, throws exception otherwise
	 */
	boolean check() {
		script.echo "Checking Xcode installation..."

		// Check 1: Verify command line tools with xcode-select -p
		script.echo "Checking command line tools with xcode-select -p..."
		try {
			def xcodePath = script.sh(
				script: 'xcode-select -p',
				returnStdout: true
			).trim()
			script.echo "✓ Command line tools found at: ${xcodePath}"
		} catch (Exception e) {
			throw new Exception("✗ Command line tools not found (xcode-select -p failed): ${e.message}")
		}

		// Check 2: Verify command line tools with pkgutil
		script.echo "Checking command line tools with pkgutil..."
		try {
			def pkgInfo = script.sh(
				script: 'pkgutil --pkg-info=com.apple.pkg.CLTools_Executables',
				returnStdout: true
			).trim()

			def versionMatch = pkgInfo =~ /version: ([\d.]+)/
			if (versionMatch) {
				script.echo "✓ Command line tools package found: ${versionMatch[0][1]}"
			} else {
				throw new Exception("Could not parse package version")
			}
		} catch (Exception e) {
			throw new Exception("✗ Command line tools package not found (pkgutil): ${e.message}")
		}

		// Check 3: Verify xcodes tool is installed
		script.echo "Checking if xcodes tool is installed..."
		try {
			def xcodesVersion = script.sh(
				script: 'xcodes version',
				returnStdout: true
			).trim()
			script.echo "✓ xcodes tool found: ${xcodesVersion}"
		} catch (Exception e) {
			throw new Exception("✗ xcodes tool not installed: ${e.message}")
		}

		// Check 4: Get list of installed Xcode versions and validate each
		script.echo "Checking installed Xcode versions..."
		def installedXcodes = getInstalledXcodes()

		if (installedXcodes.isEmpty()) {
			throw new Exception("✗ No Xcode versions found installed via xcodes")
		}

		script.echo "Found ${installedXcodes.size()} Xcode version(s) installed"

		installedXcodes.each { xcodePath ->
			script.echo "\nValidating Xcode at: ${xcodePath}"

			// Check 4a: Verify license agreement
			script.echo "  Checking license status..."
			try {
				def licenseStatus = script.sh(
					script: "DEVELOPER_DIR='${xcodePath}' xcodebuild -license status",
					returnStdout: true
				).trim()

				if (licenseStatus.contains("agree")) {
					script.echo "  ✓ Licenses agreed"
				} else {
					script.echo "  ℹ License status: ${licenseStatus}"
				}
			} catch (Exception e) {
				throw new Exception("✗ Failed to check license status for ${xcodePath}: ${e.message}")
			}

			// Check 4b: Verify iphoneos SDK is available
			script.echo "  Checking iphoneos SDK..."
			try {
				def sdkPath = script.sh(
					script: "DEVELOPER_DIR='${xcodePath}' xcrun --sdk iphoneos --show-sdk-path",
					returnStdout: true
				).trim()

				if (sdkPath && !sdkPath.contains("error")) {
					script.echo "  ✓ iphoneos SDK found at: ${sdkPath}"
				} else {
					throw new Exception("SDK path is invalid or empty")
				}
			} catch (Exception e) {
				throw new Exception("✗ iphoneos SDK not available for ${xcodePath}: ${e.message}")
			}
		}

		script.echo "\n✓ All Xcode checks passed!"
		return true
	}

	/**
	 * Gets list of installed Xcode paths from xcodes installed command
	 *
	 * @return List of absolute paths to installed Xcode versions
	 */
	private List<String> getInstalledXcodes() {
		try {
			def output = script.sh(
				script: 'xcodes installed',
				returnStdout: true
			).trim()

			List<String> paths = []

			output.split('\n').each { line ->
				// Parse lines from xcodes installed output
				// Expected format: /Applications/Xcode_XX.X.app or similar
				line = line.trim()
				if (line && line.startsWith('/')) {
					// Handle cases where the path might have additional info
					def pathPart = line.split(/\s+/)[0]
					if (pathPart.contains('.app')) {
						paths.add(pathPart)
					}
				}
			}

			return paths
		} catch (Exception e) {
			throw new Exception("Failed to get installed Xcode versions from xcodes: ${e.message}")
		}
	}
}
