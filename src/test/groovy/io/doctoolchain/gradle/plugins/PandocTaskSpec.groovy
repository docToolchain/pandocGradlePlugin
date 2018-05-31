package io.doctoolchain.gradle.plugins

import io.doctoolchain.gradle.pandoc.PandocTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class PandocTaskSpec extends Specification {

    Project project = ProjectBuilder.builder().build()

    void 'Configure PandocTask'() {
        when: 'Configuring a task'

        PandocTask pandocTask

        project.allprojects {
            apply plugin: 'io.doctoolchain.pandoc'

            pandocTask = tasks.create('runPandoc', PandocTask)

            runPandoc {
                sourceFile 'foo'
                outputFile 'bar'
                args '-a', '-b'
            }
        }

        then:
        pandocTask.getArgs() == ['-a','-b']
        pandocTask.getSourceFile() == project.file('foo')
        pandocTask.getOutputFile() == project.file('bar')
    }
}