package org.grails.spring.scope

import org.springframework.context.annotation.ScopeMetadataResolver
import org.springframework.context.annotation.ScopeMetadata
import org.springframework.beans.factory.config.BeanDefinition

/**
 * @author Graeme Rocher
 * @since 1.0
 * 
 * Created: May 14, 2009
 */

class PrototypeScopeMetadataResolver implements ScopeMetadataResolver {
    public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
        return new ScopeMetadata(scopeName:BeanDefinition.SCOPE_PROTOTYPE)
    }

}