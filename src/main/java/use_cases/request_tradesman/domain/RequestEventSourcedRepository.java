package use_cases.request_tradesman.domain;

import kernel.EventSourcedRepository;

import java.util.List;

public interface RequestEventSourcedRepository extends EventSourcedRepository<RequestId, Request> {
    List<Request> findAll();
}
