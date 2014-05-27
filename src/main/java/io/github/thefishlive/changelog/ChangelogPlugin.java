package io.github.thefishlive.changelog;

import io.github.thefishlive.changelog.tasks.ChangelogTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class ChangelogPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        Task task = project.getTasks().create("changelog", ChangelogTask.class);
        task.setGroup("Changelog");
        task.setDescription("Generates a changelog from the git history of the project");
    }

}
