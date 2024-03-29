package com.example.myproject.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsView {

    private final int authRequests;
    private final int anonRequests;

    public StatsView(int authRequests, int anonRequests) {
        this.authRequests = authRequests;
        this.anonRequests = anonRequests;
    }

    public int getTotalRequests() {
        return anonRequests + authRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }


    public int getAnonRequests() {
        return anonRequests;
    }

}
