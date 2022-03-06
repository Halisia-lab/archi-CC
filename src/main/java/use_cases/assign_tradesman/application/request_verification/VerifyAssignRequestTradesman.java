package use_cases.assign_tradesman.application.request_verification;

import domain.Role;
import kernel.NotValidTradesmanException;
import kernel.Verification;
import domain.MemberEventSourcedRepository;
import use_cases.assign_tradesman.application.request.CreateAssignRequest;

public class VerifyAssignRequestTradesman implements Verification<CreateAssignRequest> {
    MemberEventSourcedRepository memberEventSourcedRepository;
    public VerifyAssignRequestTradesman(MemberEventSourcedRepository memberEventSourcedRepository) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
    }

    @Override
    public void validate(CreateAssignRequest createAssignRequest) {
        if( memberEventSourcedRepository.findById(createAssignRequest.tradesmanId) == null
                || memberEventSourcedRepository.findById(createAssignRequest.tradesmanId).getRole() != Role.TRADESMAN) {
            throw NotValidTradesmanException.withId(createAssignRequest.tradesmanId);
        }
    }
}
