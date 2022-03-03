package use_cases.request_trademan.application.request_verification;

import kernel.Verification;
import use_cases.request_trademan.application.request.CreateRequest;
import use_cases.request_trademan.exception.NotValidProjectRequestException;

public class VerifyRequestProject implements Verification<CreateRequest> {

        public VerifyRequestProject() {
                }

        @Override
        public void validate(CreateRequest createRequest) {

                if( (int) createRequest.project.getDailyTax() <= 0
                        || createRequest.project.getSkills().size() == 0
                        || createRequest.project.getJobs().size() == 0
                        || createRequest.project.getRegion() == null) {
                        throw NotValidProjectRequestException.withName(createRequest.project.getName());
                }
        }
}
