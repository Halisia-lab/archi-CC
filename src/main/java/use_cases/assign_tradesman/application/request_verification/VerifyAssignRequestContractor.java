package use_cases.assign_tradesman.application.request_verification;

import domain.Role;
import kernel.Verification;
import domain.MemberEventSourcedRepository;
import use_cases.assign_tradesman.application.request.CreateAssignRequest;
import kernel.NotValidContractorRequestException;

public class VerifyAssignRequestContractor implements Verification<CreateAssignRequest> {
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    public VerifyAssignRequestContractor(MemberEventSourcedRepository memberEventSourcedRepository) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
    }

    @Override
    public void validate(CreateAssignRequest createAssignRequest) {
        if( memberEventSourcedRepository.findById(createAssignRequest.contractorId) == null
                || memberEventSourcedRepository.findById(createAssignRequest.contractorId).getRole() != Role.CONTRACTOR) {
            throw NotValidContractorRequestException.withId(createAssignRequest.contractorId);
        }
    }
}
