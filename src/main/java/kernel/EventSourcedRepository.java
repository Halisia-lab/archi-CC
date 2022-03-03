package kernel;

import use_cases.add_member.exception.NoSuchMemberException;

public interface EventSourcedRepository<VOID, E>{
    VOID nextIdentity();

    E findById(VOID id) throws NoSuchMemberException;

    void add(E entity);

    void delete(VOID id);
}
