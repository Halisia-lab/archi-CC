package use_cases.request_trademan.domain;

import kernel.EventSourcedRepository;

import java.util.List;
import java.util.Optional;

public interface RequestEventSourcedRepository extends EventSourcedRepository<RequestId, Request> {
    List<Request> findAll();
}
