package io.doctoolchain.gradle.pandoc

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @since
 */
@CompileStatic
class PandocPlugin implements Plugin<Project> {

    static final String PANDOC_EXT = 'pandoc'

    @Override
    void apply(Project project) {

        project.extensions.create(PANDOC_EXT,PandocExtension,project)
    }
}
