package com.rodmccutcheon.pensionator.domain;

public class Couple {

    private Client client;
    private Client partner;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getPartner() {
        return partner;
    }

    public void setPartner(Client partner) {
        this.partner = partner;
    }
}
