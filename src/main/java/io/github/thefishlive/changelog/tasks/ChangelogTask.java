package io.github.thefishlive.changelog.tasks;

import com.google.common.io.Files;
import groovy.lang.Closure;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.resources.Resource;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ChangelogTask extends DefaultTask {

    @Input
    private String generator;

    @TaskAction
    public void changelog() {
        try {
            Git git = Git.open(getProject().getRootDir());

            LogCommand command = git.log();

            Iterable<RevCommit> log = command.call();
            StringBuilder changelog = new StringBuilder();

            for (RevCommit commit : log) {
                changelog.append("- [").append(commit.getAuthorIdent().getName()).append("] ");
                changelog.append("[").append(commit.abbreviate(10).name()).append("] ");
                changelog.append(commit.getShortMessage()).append("\n");
            }

            Files.write(changelog.toString().getBytes(), getOutput());
            getProject().getArtifacts().add("archives", getOutput());
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
    }

    public File getOutput() {
        return new File(getProject().getBuildDir(), "changelog");
    }
}
