package com.test;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.boot.internal.EnversIntegrator;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;


public class EkycEnversIntegrator extends EnversIntegrator {

    @Override
    public void integrate(Metadata metadata, BootstrapContext bootstrapContext, SessionFactoryImplementor sessionFactory) {
        EventListenerRegistry listenerRegistry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        if (listenerRegistry != null) {
            listenerRegistry.addDuplicationStrategy(EnversListenerDuplicationStrategy.INSTANCE);
            EnversService enversService = sessionFactory.getServiceRegistry().getService(EnversService.class);
            if (enversService != null && enversService.getEntitiesConfigurations().hasAuditedEntities())
                listenerRegistry.appendListeners(EventType.POST_UPDATE, new PostUpdateHistoryChanges(enversService));
        }
    }

}
