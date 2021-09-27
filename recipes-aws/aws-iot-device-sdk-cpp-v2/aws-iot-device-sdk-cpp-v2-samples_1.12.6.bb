require aws-iot-device-sdk-cpp-v2.inc

DEPENDS += "aws-iot-device-sdk-cpp-v2"

RDEPENDS:${PN} += "binutils"

OECMAKE_SOURCEPATH = "${S}/samples"
