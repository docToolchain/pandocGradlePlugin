package io.doctoolchain.gradle.pandoc

import groovy.transform.CompileStatic
import org.gradle.api.Project

/**
 * @since
 */
@CompileStatic
class PandocExtension {

    // This line is also read by the build - be care ful if you modify it's layout
    static final String DEFAULT_PANDOC_VERSION = '2.2.1'

    String version = DEFAULT_PANDOC_VERSION

    PandocExtension(Project project1) {
        this.project = project1
    }

    File getPandocExecutable() {
        Downloader downloader = new Downloader(getVersion(),this.project)

        new File(downloader.distributionRoot,Downloader.OS.isWindows() ? 'pandoc.exe' : 'bin/pandoc')
    }

    private final Project project
}
