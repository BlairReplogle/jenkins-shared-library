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
	println("=== OS Information ===")
	println("OS Name: ${osInfo.name}")
	println("OS Version: ${osInfo.version}")
	println("Is Mac: ${osInfo.isMac}")
	println("Is Linux: ${osInfo.isLinux}")
	println("=======================")

	return osInfo
}
