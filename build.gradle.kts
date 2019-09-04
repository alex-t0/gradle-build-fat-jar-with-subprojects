allprojects {
    tasks.register("listProjects") {
        doLast {
            println("Project ${this.project.name}")
        }
    }
}

