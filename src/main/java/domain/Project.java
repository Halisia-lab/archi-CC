package domain;

import java.time.Duration;
import java.util.List;

public final class Project {

    private String name;
    private Duration duration;
    private Number dailyTax;
    private List<ProjectSkills> skills;
    private List<String> jobs;
    private ProjectLocalization region;

    public Project(String name, Duration duration, Number dailyTax, ProjectLocalization region, List<ProjectSkills> skills, List<String> jobs) {

        this.name = name;
        this.duration = duration;
        this.dailyTax = dailyTax;
        this.skills = skills;
        this.jobs = jobs;
        this.region = region;
    }

    public static Project of(String name, Duration duration, Number dailyTax, ProjectLocalization region, List<ProjectSkills> skills, List<String> jobs) {
        return new Project(name, duration, dailyTax, region, skills, jobs);
    }


    public String getName() {
        return name;
    }

    public ProjectLocalization getRegion() {
        return region;
    }

    public Number getDailyTax() {
        return dailyTax;
    }

    public List<ProjectSkills> getSkills() {
        return skills;
    }

    public List<String> getJobs() {
        return jobs;
    }



    @Override
    public String toString() {
        return "Project{" +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", region=" + region + '\'' +
                ", dailyTax=" + dailyTax + '\'' +
                '}';
    }
}
