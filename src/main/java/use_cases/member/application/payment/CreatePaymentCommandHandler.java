package use_cases.member.application.payment;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.member.application.payment_verification.VerifyPaymentApplication;
import use_cases.member.application.payment_verification.VerifyPaymentApplicationHandler;
import use_cases.member.domain.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, PaymentId> {
    private ScheduledExecutorService executorService;
    private final PaymentEventSourcedRepository paymentEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final VerifyPaymentApplicationHandler verifyPaymentApplicationHandler;
    public CreatePaymentCommandHandler(PaymentEventSourcedRepository paymentEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher, VerifyPaymentApplicationHandler verifyPaymentApplicationHandler) {
        this.paymentEventSourcedRepository = paymentEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.verifyPaymentApplicationHandler = verifyPaymentApplicationHandler;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public PaymentId handle(CreatePayment createPayment) {
        final PaymentId paymentId = paymentEventSourcedRepository.nextIdentity();
        Payment payment = Payment.of(paymentId, createPayment.amount, createPayment.owner, createPayment.createDate);
        verifyPaymentApplicationHandler.handle(new VerifyPaymentApplication(paymentId, createPayment));
        paymentEventSourcedRepository.add(payment);
        createPayment.owner.createPayment = createPayment;
        eventEventDispatcher.dispatch(new CreatePaymentApplicationEvent(paymentId));
        return paymentId;
    }
}
