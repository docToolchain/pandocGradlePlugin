package io.doctoolchain.gradle.pandoc

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class DownloaderSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    void 'Downloading a specific verion of pandoc'() {
        given: 'a specific version of pandoc'
        def version = "2.2.1"
        Downloader downloader = new Downloader(version, project)

        when: 'the file is requested'
        def createdDistribution = downloader.distributionRoot

        then: 'the package will be unpacked in the Gradle cache'
        createdDistribution.exists()

    }
}