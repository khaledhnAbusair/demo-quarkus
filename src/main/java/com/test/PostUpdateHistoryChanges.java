package com.test;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

import java.util.List;

public class PostUpdateHistoryChanges extends EnversPostUpdateEventListenerImpl {
    private static final List<String> SKIP_KEYS = List.of("reasons", "lastActionUser", "attachments", "stepId", "stepName", "availableRoles", "isFircoApproved", "fircoRefNumber");
    private static final String PAYLOAD = "payload";

    public PostUpdateHistoryChanges(EnversService enversService) {
        super(enversService);

    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        super.onPostUpdate(event);
    }
}
