package use_cases.match_tradesman.domain;


import domain.*;
import kernel.Engine;
import use_cases.match_tradesman.application.VerifyMatchRequest;
import use_cases.match_tradesman.application.VerifyMatchingTradesman;
import use_cases.request_tradesman.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Engine
public final class MatchTradesManEngine implements Predicate<Tradesman> {

    private final RequestEventSourcedRepository requestEventSourcedRepository;
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    private RequestId requestId;
    private List<Tradesman> candidates;
    private Tradesman bestFitTradesman;

    public MatchTradesManEngine(RequestEventSourcedRepository requestEventSourcedRepository, MemberEventSourcedRepository memberEventSourcedRepository, RequestId requestId) {
        this.requestEventSourcedRepository = requestEventSourcedRepository;
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.requestId = requestId;
        this.candidates = new ArrayList<>();
        this.bestFitTradesman = null;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public List<Tradesman> searchCandidates() {
        final Stream<Member> allTradesMen = memberEventSourcedRepository.findAll().stream().filter(member -> member.getRole() == Role.TRADESMAN);
        return allTradesMen.map(member -> (Tradesman) member).collect(Collectors.toList());
    }

    public Tradesman findBestFitTradesman() {
        try {
            VerifyMatchRequest verifyMatchRequest = new VerifyMatchRequest(requestEventSourcedRepository);
            verifyMatchRequest.validate(getRequestId());

            Project requestProjectNeeds = requestEventSourcedRepository.findById(requestId).getProject();
            candidates = searchCandidates();

            if(candidates.size() == 0) return null;

            Tradesman candidateWithMaxPoints = candidates.get(0);
            for (Tradesman candidate:candidates) {
                if(calculatePoints(candidate,requestProjectNeeds) > calculatePoints(candidateWithMaxPoints,requestProjectNeeds)) {
                    candidateWithMaxPoints = candidate;
                }
            }
            return candidateWithMaxPoints;
        } catch (Exception e) {
            System.out.println("ERROR DURING BEST FIT TRADESMAN FINDING: " + e);
        }
        return null;
    }

    public int calculatePoints(Tradesman candidate, Project project) {
        int skills = 0, dailyTax = 0, region = 0;

        for(ProjectSkills skillRequired: project.getSkills()) {
            if(candidate.getSkills() != null && candidate.getSkills().contains(skillRequired)) skills += 3;
        }
        if(candidate.getAvailabilityZones() != null && candidate.getAvailabilityZones().contains(project.getRegion())) region += 2;
        if(project.getDailyTax() != null && candidate.getDailyTaxMin() != null)
            if((int) project.getDailyTax() >= (int) candidate.getDailyTaxMin()) dailyTax++;

        return skills + region + dailyTax;
    }

    @Override
    public String toString() {
        return "Match{" +
                "requestId='" + requestId + '\'' +
                ", candidates='" + candidates + '\'' +
                '}';
    }

    @Override
    public boolean test(Tradesman tradesman) {
        try {
            VerifyMatchingTradesman verifyMatchingTradesman = new VerifyMatchingTradesman(requestEventSourcedRepository);
            verifyMatchingTradesman.validate(tradesman);
            return tradesman.equals(this.findBestFitTradesman());
        } catch (Exception e) {
            System.out.println("ERROR during MatchTradesManEngine : " + e.getMessage());
        }
        return false;
    }
}
