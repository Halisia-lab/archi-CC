package use_cases.request_trademan.application.request_verification;

import kernel.Verification;
import use_cases.add_member.domain.MemberEventSourcedRepository;
import use_cases.add_member.domain.Role;
import use_cases.request_trademan.application.request.CreateRequest;
import use_cases.request_trademan.exception.NotValidContractorRequestException;

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
