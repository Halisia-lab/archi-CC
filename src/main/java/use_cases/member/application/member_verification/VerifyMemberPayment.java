package use_cases.member.application.member_verification;

import kernel.Verification;
import use_cases.member.application.member.CreateMember;
import use_cases.member.application.payment.CreatePayment;
import use_cases.member.exception.PaymentNotMadeException;

public class VerifyMemberPayment implements Verification<CreatePayment> {
    private final CreateMember createMember;

    public VerifyMemberPayment(CreateMember createMember) {
        this.createMember = createMember;
    }

    @Override
    public void validate(CreatePayment createPayment) {
        if(createPayment == null) {
            throw PaymentNotMadeException.withAccount(createMember.email);
        }
    }
}
