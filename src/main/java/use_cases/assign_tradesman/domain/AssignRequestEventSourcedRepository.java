package use_cases.assign_tradesman.domain;

import kernel.EventSourcedRepository;

import java.util.List;

public interface AssignRequestEventSourcedRepository extends EventSourcedRepository<AssignRequestID, AssignRequest> {
    List<AssignRequest> findAll();
}
