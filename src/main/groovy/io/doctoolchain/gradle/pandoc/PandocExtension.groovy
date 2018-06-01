package io.doctoolchain.gradle.pandoc

import groovy.transform.CompileStatic
import org.gradle.api.Project

/**
 * @since
 */
@CompileStatic
class PandocExtension {


    String version = '2.2.1'

    PandocExtension(Project project1) {
        this.project = project1
    }

    File getPandocExecutable() {
        Downloader downloader = new Downloader(getVersion(),this.project)

        new File(downloader.distributionRoot,Downloader.os.isWindows() ? 'pandoc.exe' : 'bin/pandoc')
    }

    private final Project project
}
