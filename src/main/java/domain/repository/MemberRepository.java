package domain.repository;

import domain.model.Member;
import domain.model.MemberId;

import java.util.Optional;

public interface MemberRepository {
    MemberId nextIdentity();
    Member findById(MemberId id);
    Optional<Member> findByEmail(String email);
    void add(Member member);
}
