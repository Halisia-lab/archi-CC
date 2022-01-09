package use_cases.member.domain;

import kernel.EventSourcedRepository;

import java.util.List;
import java.util.Optional;

public interface MemberEventSourcedRepository extends EventSourcedRepository<MemberId, Member> {
    List<Member> findAll();
    Optional<Member> findByEmail(String email);
}
