package domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public final class Project {

//    private final ProjectId projectId;
    private String name;
    private LocalDate startDate;
    private Duration duration;
    private Number dailyTax;
    private List<ProjectSkills> skills;
    private List<String> jobs;
    private ProjectLocalization region;

    public Project(String name, LocalDate startDate,Duration duration, Number dailyTax, ProjectLocalization region, List<ProjectSkills> skills, List<String> jobs) {

        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.dailyTax = dailyTax;
        this.skills = skills;
        this.jobs = jobs;
        this.region = region;
    }

    public static Project of(String name, LocalDate startDate, Duration duration, Number dailyTax, ProjectLocalization region, List<ProjectSkills> skills, List<String> jobs) {
        return new Project(name, startDate, duration, dailyTax, region, skills, jobs);
    }

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

    public Duration getDuration() {
        return duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Project{" +
                ", name='" + name + '\'' +
                ", startDate='" + startDate.toString() + '\'' +
                ", duration='" + duration + '\'' +
                ", region=" + region + '\'' +
                ", dailyTax=" + dailyTax + '\'' +
                '}';
    }
}
