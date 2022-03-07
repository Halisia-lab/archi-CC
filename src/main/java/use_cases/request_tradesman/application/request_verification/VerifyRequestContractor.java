package use_cases.request_tradesman.application.request_verification;

import kernel.Verification;
import domain.MemberEventSourcedRepository;
import domain.Role;
import use_cases.request_tradesman.application.request.CreateRequest;
import kernel.NotValidContractorRequestException;

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
