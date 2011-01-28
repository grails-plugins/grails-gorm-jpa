/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.jpa

import org.grails.datastore.gorm.jpa.bean.factory.JpaMappingContextFactoryBean
import org.grails.datastore.gorm.jpa.bean.factory.JpaDatastoreFactoryBean
import org.grails.datastore.gorm.jpa.JpaGormEnhancer
import org.grails.datastore.gorm.jpa.support.*
import org.grails.datastore.gorm.support.DatastorePersistenceContextInterceptor

import org.springframework.datastore.mapping.web.support.*
import org.springframework.orm.jpa.JpaTransactionManager;
/**
 * @author Graeme Rocher
 * @since 0.1
 * 
 * Created: Apr 17, 2009
 */
public class JpaPluginSupport {



  static doWithSpring = {
/*    xmlns context:"http://www.springframework.org/schema/context"
    context.'component-scan'( type:"annotation", filter: Entity.name, 'scope-resolver':PrototypeScopeMetadataResolver.name )*/

    jpaMappingContext(JpaMappingContextFactoryBean) {
      grailsApplication = ref('grailsApplication')
      pluginManager = ref('pluginManager')
    }

	jpaDatastore(JpaDatastoreFactoryBean) { 
		entityManagerFactory = ref("entityManagerFactory")
		mappingContext = jpaMappingContext
	}
    jpaPersistenceInterceptor(JpaPersistenceContextInterceptor, ref("jpaDatastore"))

    if (manager?.hasGrailsPlugin("controllers")) {
        jpaDatastoreOpenSessionInViewInterceptor(org.grails.datastore.gorm.jpa.support.JpaOpenSessionInViewInterceptor) {
            datastore = ref("jpaDatastore")
        }

        if (getSpringConfig().containsBean("controllerHandlerMappings")) {
            controllerHandlerMappings.interceptors <<  jpaDatastoreOpenSessionInViewInterceptor
        }
        if (getSpringConfig().containsBean("annotationHandlerMapping")) {
            if (annotationHandlerMapping.interceptors) {
                annotationHandlerMapping.interceptors << jpaDatastoreOpenSessionInViewInterceptor
            }
            else {
                annotationHandlerMapping.interceptors = [jpaDatastoreOpenSessionInViewInterceptor]
            }
        }
    }
	
  }

  static doWithApplicationContext = { applicationContext ->
		def transactionManager = applicationContext.getBean(JpaTransactionManager)
		def jpaDatastore = applicationContext.getBean("jpaDatastore")
		
		def enhancer = new JpaGormEnhancer(jpaDatastore, transactionManager)
		enhancer.enhance()
  }
}