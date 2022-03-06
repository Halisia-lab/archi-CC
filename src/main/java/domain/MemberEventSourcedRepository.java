package domain;

import domain.Member;
import domain.MemberId;
import kernel.EventSourcedRepository;

import java.util.List;
import java.util.Optional;

public interface MemberEventSourcedRepository extends EventSourcedRepository<MemberId, Member> {
    List<Member> findAll();
    Optional<Member> findByEmail(String email);
}
