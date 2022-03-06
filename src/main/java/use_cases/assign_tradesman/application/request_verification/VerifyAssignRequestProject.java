package use_cases.assign_tradesman.application.request_verification;

import kernel.NotValidProjectRequestException;
import kernel.Verification;
import use_cases.assign_tradesman.application.request.CreateAssignRequest;

public class VerifyAssignRequestProject implements Verification<CreateAssignRequest> {

        public VerifyAssignRequestProject() {
                }

        @Override
        public void validate(CreateAssignRequest createAssignRequest) {

                if( (int) createAssignRequest.project.getDailyTax() <= 0
                        || createAssignRequest.project.getSkills().size() == 0
                        || createAssignRequest.project.getJobs().size() == 0
                        || createAssignRequest.project.getRegion() == null
                        || createAssignRequest.project.getStartDate() == null
                        || createAssignRequest.project.getDuration() == null) {
                        throw NotValidProjectRequestException.withName(createAssignRequest.project.getName());
                }
        }
}
