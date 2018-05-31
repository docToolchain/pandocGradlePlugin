package io.doctoolchain.gradle.pandoc

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.ysb33r.grolifant.api.StringUtils

/**
 * @since
 */
@CompileStatic
class PandocTask extends DefaultTask {

    private Object  input
    private Object target
    final private List<Object> arguments = []

    @InputFile
    File getSourceFile() {
        this.input ? project.file(this.input) : null
    }

    void setSourceFile(Object f) {
        this.input = f
    }

    @OutputFile
    File getOutputFile() {
        this.target ? project.file(this.target) : null
    }

    void setOutputFile(Object f) {
        this.target = f
    }

    @Input
    List<String> getArgs() {
        StringUtils.stringize(this.arguments)
    }

    void setArgs(Iterable<Object> a) {
        arguments.clear()
        arguments.addAll(a)
    }

    void args(Object... a) {
        arguments.addAll(a)
    }

    @TaskAction
    void exec() {

        File pandocExec = project.getExtensions().getByType(PandocExtension).pandocExecutable

        getOutputFile().parentFile.mkdirs()
        run(pandocExec)
    }

    @CompileDynamic
    void run(File pandoc) {
        project.exec {
            executable pandoc
            args getArgs()
            args '-o', getOutputFile().absolutePath
            args getSourceFile().absolutePath
        }
    }
}
