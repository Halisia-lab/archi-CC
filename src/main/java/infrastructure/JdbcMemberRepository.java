package infrastructure;

import domain.model.Member;
import domain.model.MemberId;
import domain.repository.MemberRepository;

import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
    @Override
    public MemberId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Member findById(MemberId id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void add(Member member) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
