package kernel;

import java.util.List;

public interface Entity<VOID extends ValueObjectID> {
    VOID id();
    List<DomainEvent> recordedEvents();
}