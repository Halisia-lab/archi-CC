package use_cases.member.infrastructure;

import kernel.DomainEvent;
import use_cases.member.domain.Member;
import use_cases.member.domain.MemberEventSourcedRepository;
import use_cases.member.domain.MemberId;
import use_cases.member.exception.NoSuchMemberException;
import use_cases.member.exception.NotValidApplicationException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryMemberRepository implements MemberEventSourcedRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<MemberId, List<DomainEvent>> data = new ConcurrentHashMap<>();

    @Override
    public MemberId nextIdentity() {
        return new MemberId(count.incrementAndGet());
    }

    @Override
    public Member findById(MemberId id) {
        final List<DomainEvent> recordedEvents = data.get(id);
        if (recordedEvents == null) {
            throw NoSuchMemberException.withId(id);
        }
        return Member.of(id, recordedEvents);
    }

    public Optional<Member> findByEmail(String email) {
        final List<Member> members = this.findAll();
        Optional<Member> result = members.stream().filter(m -> m.getEmail().equals(email)).findAny();
        if (result.isPresent()) {
            throw NotValidApplicationException.withExistingEmail(email);
        }
        return result;
    }

    @Override
    public void add(Member member) {
        final MemberId memberId = member.id();
        var domainEvents = data.get(memberId);
        if (domainEvents == null) {
            domainEvents = new ArrayList<>();
        }
        domainEvents.addAll(member.recordedEvents());
        data.put(member.id(), domainEvents);
    }

    @Override
    public void delete(MemberId id) {
        data.remove(id);
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();
        final Set<Map.Entry<MemberId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<MemberId, List<DomainEvent>> entry : entries) {
            result.add(Member.of(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
