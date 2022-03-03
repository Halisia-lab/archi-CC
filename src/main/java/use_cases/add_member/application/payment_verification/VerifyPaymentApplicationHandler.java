package use_cases.add_member.application.payment_verification;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

public class VerifyPaymentApplicationHandler implements CommandHandler<VerifyPaymentApplication, Void> {

    private final EventDispatcher<Event> eventEventDispatcher;

    public VerifyPaymentApplicationHandler(EventDispatcher<Event> eventEventDispatcher) {
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public Void handle(VerifyPaymentApplication command) {

        VerifyPaymentDate verifyPaymentDate = new VerifyPaymentDate();
        verifyPaymentDate.validate(command.createPayment);

        VerifyPaymentHolderName verifyPaymentHolderName = new VerifyPaymentHolderName();
        verifyPaymentHolderName.validate(command.createPayment);

        VerifyPaymentCCNumber verifyPaymentCCNumber = new VerifyPaymentCCNumber();
        verifyPaymentCCNumber.validate(command.createPayment.creditCard.getNumber());

        VerifyPaymentAmount verifyPaymentAmount = new VerifyPaymentAmount();
        verifyPaymentAmount.validate(command.createPayment);

        eventEventDispatcher.dispatch(new VerifyPaymentApplicationEvent(command.paymentId));
        return null;
    }
}
