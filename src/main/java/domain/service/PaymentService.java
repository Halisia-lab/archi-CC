package domain.service;

import domain.model.CreditCard;
import domain.model.Member;
import domain.exception.NotValidCreditCardException;

import java.time.LocalDate;

public class PaymentService {

    public PaymentService() {
    }

    public void processPaymentFor(Member member) {
        verifyPaymentOf(member);
        System.out.println("Successful payment");
    }

    public void verifyPaymentOf(Member member) {
        CreditCard creditCard = member.getCreditCard();
        CreditCardNumberValidatorService creditCardNumberValidatorService = new CreditCardNumberValidatorService();
        if(creditCard == null) {
            throw NotValidCreditCardException.noCreditCard();
        }
        if(!creditCardNumberValidatorService.validateThatOf(member)) {
            throw NotValidCreditCardException.withNumber(creditCard.getNumber());
        }
        if(creditCard.getExpirationDate().isBefore(LocalDate.now())) {
            throw NotValidCreditCardException.withDate(creditCard.getExpirationDate());
        }
        if(!creditCard.getHolderName().equalsIgnoreCase(member.getLastname() + " " + member.getFirstname())) {
            throw NotValidCreditCardException.withName(creditCard.getHolderName());
        }
    }
}
