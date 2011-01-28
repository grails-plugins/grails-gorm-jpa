package org.acme

import grails.gorm.JpaEntity

/**
 * @author Graeme Rocher
 * @since 1.0
 * 
 * Created: May 13, 2009
 */

@JpaEntity
class Person {
	Long version
    String name
    Integer age
    String leaveMe
    Address address

	static hasMany = [roles:Role]
	static transients = ['leaveMe']
    static constraints = {
        name blank:false
		age nullable:true
		leaveMe nullable:true
		address nullable:true
    }
}