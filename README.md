gradle-changelog
================

This gradle plugin will create a changelog from your git commit history.

Download
================

To add to a build simple add these lines to the buildscript at the top

    buildscript {
        repositories {
            name = "changelog"
            url = "http://thefishlive.github.io/gradle-changelog"
        }
        dependencies {
            classpath "io.github.thefishlive:ChangeLog:1.0.0"
        }
    }
    
    apply plugin: "changelog"

Then to create a changelog just run the task

    changelog
    

