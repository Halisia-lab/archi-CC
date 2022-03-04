package use_cases.match_tradesman.application;

import domain.Tradesman;
import kernel.Verification;
import use_cases.match_tradesman.exception.NotValidRequestMatchException;
import use_cases.match_tradesman.exception.NotValidTradesmanMatchException;
import use_cases.request_tradesman.domain.RequestEventSourcedRepository;
import use_cases.request_tradesman.domain.RequestId;


public class VerifyMatchingTradesman implements Verification<Tradesman> {
        RequestEventSourcedRepository requestEventSourcedRepository;

        public VerifyMatchingTradesman(RequestEventSourcedRepository requestEventSourcedRepository) {
                this.requestEventSourcedRepository = requestEventSourcedRepository;
        }

        @Override
        public void validate(Tradesman tradesman) {
                if( tradesman == null ) {
                        throw NotValidTradesmanMatchException.withTradesman(tradesman);
                }

        }

}
