package use_cases.request_trademan.domain;

import kernel.DomainEvent;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.domain.Address;
import use_cases.add_member.domain.MemberId;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectEvent implements DomainEvent {

    private final ProjectId id;
    private final String name;
    private final Duration duration;
    private final Number dailyTax;
    private final ProjectLocalization region;
    private final List<ProjectSkills> skills;
    private final List<String> jobs;

    public CreateProjectEvent(ProjectId id, String name, Duration duration, Number dailyTax, ProjectLocalization region, List<ProjectSkills> skills, List<String> jobs) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.dailyTax = dailyTax;
        this.region = region;
        this.skills = skills;
        this.jobs = jobs;
    }

    public ProjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
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

    public ProjectLocalization getRegion() {
        return region;
    }
}
