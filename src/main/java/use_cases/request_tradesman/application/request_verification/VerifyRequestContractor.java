package use_cases.request_tradesman.application.request_verification;

import kernel.Verification;
import use_cases.add_member.domain.MemberEventSourcedRepository;
import domain.Role;
import use_cases.request_tradesman.application.request.CreateRequest;
import use_cases.request_tradesman.exception.NotValidContractorRequestException;

public class VerifyRequestContractor implements Verification<CreateRequest> {
    MemberEventSourcedRepository memberEventSourcedRepository;
    public VerifyRequestContractor(MemberEventSourcedRepository memberEventSourcedRepository) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
    }

    @Override
    public void validate(CreateRequest createRequest) {
        if( memberEventSourcedRepository.findById(createRequest.contractorId) == null
                || memberEventSourcedRepository.findById(createRequest.contractorId).getRole() != Role.CONTRACTOR) {
            throw NotValidContractorRequestException.withId(createRequest.contractorId);
        }
    }
}