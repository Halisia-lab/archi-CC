package use_cases.match_tradesman.application;

import kernel.Verification;
import use_cases.add_member.application.member.CreateMember;
import use_cases.match_tradesman.domain.MatchTradesManEngine;
import use_cases.match_tradesman.exception.NotValidRequestMatchException;
import use_cases.request_tradesman.application.request.CreateRequest;
import use_cases.request_tradesman.application.request_verification.VerifyRequestApplicationHandler;
import use_cases.request_tradesman.domain.Request;
import use_cases.request_tradesman.domain.RequestEventSourcedRepository;
import use_cases.request_tradesman.domain.RequestId;


public class VerifyMatchRequest implements Verification<RequestId> {
        RequestEventSourcedRepository requestEventSourcedRepository;

        public VerifyMatchRequest(RequestEventSourcedRepository requestEventSourcedRepository) {
                this.requestEventSourcedRepository = requestEventSourcedRepository;
        }

        @Override
        public void validate(RequestId requestId) {
                if( requestId == null || requestEventSourcedRepository.findById(requestId) == null ) {
                        throw NotValidRequestMatchException.withId(requestId);
                }

        }

}
