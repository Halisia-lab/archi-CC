package infrastructure;

import domain.model.Member;
import domain.model.MemberId;
import domain.model.NoSuchMemberException;
import domain.repository.MemberRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMemberRepository implements MemberRepository {

    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public MemberId nextIdentity() {
        return new MemberId(count.incrementAndGet());
    }

    @Override
    public Member findById(MemberId id) {
        final Member member = data.get(id);
        if(member == null) {
            throw NoSuchMemberException.withId(id);
        }
        return member;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return data.values().stream().filter(m -> m.getEmail().equals(email)).findAny();
    }


    @Override
    public void add(Member member) {
        data.put(member.getId(), member);
    }
}
