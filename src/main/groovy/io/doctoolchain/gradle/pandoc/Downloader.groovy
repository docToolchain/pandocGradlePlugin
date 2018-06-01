package io.doctoolchain.gradle.pandoc

import groovy.transform.CompileStatic
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import org.ysb33r.grolifant.api.AbstractDistributionInstaller

/**
 * @since
 */
@CompileStatic
class Downloader extends AbstractDistributionInstaller {

    final static OperatingSystem OS = OperatingSystem.current()
    final static String baseURI = System.getProperty('io.doctoolchain.gradle.pandoc.uri') ?: 'https://github.com/jgm/pandoc/releases/download'

    Downloader(String version, Project project) {
        super("pandoc", version, "binary/pandoc", project)
    }

    @Override
    URI uriFromVersion(String version) {
        "${baseURI}/$version/pandoc-$version-${osArchitecture()}".toURI()
    }

    String osArchitecture() {
        if (OS.isWindows()) {
            System.getProperty('os.arch').contains('64') ? 'windows-x86_64.zip' : 'windows-i386.zip'
        } else if (OS.isLinux()) {
            "linux.tar.gz"
        } else if (OS.isMacOsX()) {
            "macOS.zip"
        } else {
            throw new GradleException("${OS} does not support downloading of pandoc")
        }
    }
}
