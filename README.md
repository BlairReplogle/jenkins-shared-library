# jenkins-shared-library

Jenkins shared library to add steps to configure an executors environment for different tools.

This is a simplified version of the template repository found [here](https://github.com/papiocloudsoftware/jenkins-shared-library-template)

Information about setting up build executors:
- [macos-latest](docs/macos-latest.md)
- [ubuntu-latest](docs/ubuntu-latest.md)

## ASDF Install

This Jenkins step runs `asdf install` to install all tools and versions specified in the repository's `.tool-versions` file.

If the `setup: true` parameter is provided the step will first call `asdfSetup()` before running

### Usage
```groovy
asdfInstall(setup: true)
```

### Parameters

| Parameter | Type | Default | Optional | Description |
|-----------|------|---------|-------------|-------------|
| `setup` | Boolean | `false` | Yes | If `true`, runs the `setup_asdf()` function before installing tools |

## Setup Android

This Jenkins step sets up the environment on the executor for use with android development tools.

If `ANDROID_HOME` is set the value is used, otherwise falls back to `$HOME/Library/Android/sdk`

Adds to the `PATH`
- `$ANDROID_HOME/cmdline-tools/latest/bin`
- `$ANDROID_HOME/platform-tools`
- `$ANDROID_HOME/emulator`

### Usage
```groovy
setupAndroid()
```

### Parameters

None

## Setup ASDF

This Jenkins step sets up the environment on the executor for use with `asdf`

If `ASDF_DATA_DIR` is set the value is used, otherwise falls back to `$HOME/.asdf`

Adds to the `PATH`
- `$ASDF_DATA_DIR/shims`

### Parameters

None

## Setup Homebrew

This Jenkins step sets up the environment on the executor for use with `homebrew`

If `HOMEBREW_PREFIX` is et the value is used, otherwise falls back to `/opt/homebrew`

Adds to the `PATH`
- `$HOMEBREW_PREFIX/bin`
- `$HOMEBREW_PREFIX/sbin`

### Parameters

None
