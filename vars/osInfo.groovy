//
//	osInfo.groovy
//	Returns a hash with the `name` and `version` of the OS
//		- `name`: Name of the os
//		- `version`: Version of the os
//		- `isMac`: true if os is mac
//		- `isLinux`: true if os is linux
//

def call() {
	Map osInfo = [:]

	String osName = sh(script: 'uname -s', returnStdout: true).trim().toLowerCase()
	String osVersion = sh(script: 'uname -r', returnStdout: true).trim()

	osInfo.name = osName == 'darwin' ? 'mac' : osName
	osInfo.version = osVersion
	osInfo.isMac = osName == 'darwin'
	osInfo.isLinux = osName == 'linux'

	// Print the osInfo object
	def lines = []

	lines << "=== OS Information ==="
	lines << "OS Name: ${osInfo.name}"
	lines << "OS Version: ${osInfo.version}"
	lines << "Is Mac: ${osInfo.isMac}"
	lines << "Is Linux: ${osInfo.isLinux}"
	lines << "Path: ${env.PATH}"
	lines << "======================="
	println lines.join("\n")

	return osInfo
}
