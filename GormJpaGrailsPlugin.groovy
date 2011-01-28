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

import org.springframework.beans.factory.support.*
import org.springframework.context.annotation.*
import org.springframework.core.type.filter.*
import org.springframework.beans.factory.generic.*
import javax.persistence.Entity
import org.grails.spring.scope.*
import org.grails.jpa.*

class GormJpaGrailsPlugin {
    // the plugin version
    def version = "1.0.0-M1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/conf/spring/resources.groovy",
            "grails-app/domain/org/acme/*.groovy",
            "grails-app/controllers/org/acme/*.groovy",
            "grails-app/views/error.gsp"
    ]

    def author = "Graeme Rocher"
    def authorEmail = ""
    def title = "GORM-JPA Plugin"
    def description = '''\\
A plugin that emulates the behavior of the GORM-Hibernate plugin against a standard JPA 1.0 backend
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/gorm-jpa"

    def doWithSpring = JpaPluginSupport.doWithSpring

    def doWithApplicationContext = JpaPluginSupport.doWithApplicationContext

}
