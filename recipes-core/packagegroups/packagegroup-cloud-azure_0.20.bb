DESCRIPTION = "Packages for Microsoft Azure IoT."
LICENSE = "MIT"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PR = "r0"

PACKAGES = "${PN}"

PACKAGECONFIG ??= "sdk-c python"

PACKAGECONFIG[sdk-c] = "\
    , \
    , \
    , \
    azure-iot-sdk-c \
    azure-iot-sdk-c-dev \
"

PACKAGECONFIG[python] = "\
    , \
    , \
    , \
    python3-azure-iot-device \
"

