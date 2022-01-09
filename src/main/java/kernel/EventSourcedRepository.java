package kernel;

import use_cases.member.exception.NoSuchMemberException;

public interface EventSourcedRepository<VOID, E>{
    VOID nextIdentity();

    E findById(VOID id) throws NoSuchMemberException;

    void add(E entity);

    void delete(VOID id);
}
