package use_cases.add_member.application.member_verification;

import kernel.Verification;
import use_cases.add_member.application.member.CreateMember;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.exception.PaymentNotMadeException;

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
