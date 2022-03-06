package use_cases.assign_tradesman.domain;

import domain.MemberId;
import domain.Project;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.assign_tradesman.application.booking.CreateBooking;
import use_cases.assign_tradesman.application.booking.CreateBookingCommandHandler;
import use_cases.assign_tradesman.application.request.CreateAssignRequest;
import use_cases.assign_tradesman.application.request.CreateAssignRequestCommandHandler;
import use_cases.assign_tradesman.application.request_verification.VerifyAssignRequestApplicationHandler;

public class AssignTradesmanEngine {

    private final AssignRequestEventSourcedRepository assignRequestEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final VerifyAssignRequestApplicationHandler verifyAssignRequestApplicationHandler;
    private final BookingEventSourcedRepository bookingEventSourcedRepository;

    public AssignTradesmanEngine(AssignRequestEventSourcedRepository assignRequestEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher, VerifyAssignRequestApplicationHandler verifyAssignRequestApplicationHandler, BookingEventSourcedRepository bookingEventSourcedRepository) {
        this.assignRequestEventSourcedRepository = assignRequestEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.verifyAssignRequestApplicationHandler = verifyAssignRequestApplicationHandler;
        this.bookingEventSourcedRepository = bookingEventSourcedRepository;


    }

    public BookingId doAssignTradesman(MemberId tradesmanId, MemberId contractorId, Project project){

        try {
            CreateAssignRequestCommandHandler assignRequestCommandHandler = new CreateAssignRequestCommandHandler(assignRequestEventSourcedRepository, eventEventDispatcher, verifyAssignRequestApplicationHandler);
            CreateAssignRequest createAssignRequest = new CreateAssignRequest(tradesmanId, project, contractorId);
            AssignRequestID assignRequestID = assignRequestCommandHandler.handle(createAssignRequest);
            AssignRequest assignRequest = assignRequestEventSourcedRepository.findById(assignRequestID);

            if(isAvailableTradesman(assignRequest.getTradesmanId(), assignRequest.getProject())) {
                CreateBookingCommandHandler bookingCommandHandler = new CreateBookingCommandHandler(bookingEventSourcedRepository, eventEventDispatcher);
                CreateBooking createBooking = new CreateBooking(project.getStartDate(), project.getDuration(), project, contractorId, tradesmanId);
                return bookingCommandHandler.handle(createBooking);
            } else {
                System.out.println("TRADESMAN WITH ID " + assignRequest.getTradesmanId() + " IS NOT AVAILABLE ANYMORE");
            }
            } catch (Exception e) {
                System.out.println("ERROR DURING BOOKING APPLICATION : " + e.getMessage());
            }

        return null;

    }

    private Boolean isAvailableTradesman(MemberId tradesmanId, Project project){
        return bookingEventSourcedRepository.findAllByTradesmanEndingAfterDate(tradesmanId, project.getStartDate()).size() == 0;
    }

}
