require aws-iot-device-sdk-cpp-v2.inc

FILES:${PN} += "\
    ${libdir}/*-cpp \
"

FILES:${PN}-dev += "\
    ${libdir}/*/cmake \
"
