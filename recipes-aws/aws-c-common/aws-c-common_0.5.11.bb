DESCRIPTION = "Core c99 package for AWS SDK for C. Includes cross-platform primitives, configuration, data structures, and error handling."
AUTHOR = "Amazon"
HOMEPAGE = "https://github.com/awslabs/aws-c-common"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit cmake ptest

SRC_URI = "git://github.com/awslabs/${BPN}.git;protocol=https;branch=main \
           file://Build-static-and-shared-libs.patch \
           file://0001-priority_queue.c-fix-compile-error-with-Og.patch \
           file://0001-Remove-Werror.patch \
           file://run-ptest \
           file://ptest_result.py \
"

# v0.5.11
SRCREV = "8f3ac3f087d56287eb7f428880dec134a3aa81f3"

PR = "r0"

S = "${WORKDIR}/git"

OECMAKE_GENERATOR = "Unix Makefiles"
EXTRA_OECMAKE += "${@bb.utils.contains('PTEST_ENABLED', '1', '-DCMAKE_BUILD_TYPE=Debug -DALLOW_CROSS_COMPILED_TESTS=ON', '-DBUILD_TESTING=OFF -DCMAKE_BUILD_TYPE=Release', d)}"

FILES:${PN}-dev += "\
    ${libdir}/${PN}/cmake \
"

RDEPENDS:${PN}-ptest += "cmake python3"

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests

    cp -r ${B}/ ${D}${PTEST_PATH}/build
    cp -r ${S}/ ${D}${PTEST_PATH}/src

    find ${D}${PTEST_PATH}/build -name "CMakeFiles" | xargs rm -rf
    find ${D}${PTEST_PATH}/build -name "*.so*" | xargs rm -rf
    find ${D}${PTEST_PATH}/src -name ".git" | xargs rm -rf

    install -m 0755 ${WORKDIR}/ptest_result.py ${D}${PTEST_PATH}/
}

