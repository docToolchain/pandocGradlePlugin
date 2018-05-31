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

    final static OperatingSystem os = OperatingSystem.current()

    Downloader(String version, Project project) {
        super("pandoc", version, "binary/pandoc", project)
    }

    @Override
    URI uriFromVersion(String version) {
        return "https://github.com/jgm/pandoc/releases/download/$version/pandoc-$version-${osArchitecture()}".toURI()
    }

    String osArchitecture() {
        if (os.isWindows()) {
            return "windows-x86_64.zip"
        }

        if (os.isLinux()){
            return "linux.tar.gz"
        }

        if (os.isMacOsX()) {
            return "macOS.zip"
        }

        throw new GradleException("Unssuported OS")
    }
}
