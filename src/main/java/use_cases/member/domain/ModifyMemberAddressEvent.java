package use_cases.member.domain;

import kernel.DomainEvent;

public class ModifyMemberAddressEvent implements DomainEvent {

    private final Address address;

    public ModifyMemberAddressEvent(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

}
