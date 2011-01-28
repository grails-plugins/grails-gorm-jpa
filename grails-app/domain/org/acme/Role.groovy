package org.acme

import grails.gorm.JpaEntity
/**
 * @author Graeme Rocher
 * @since 1.1
 */

@JpaEntity
class Role {

  String name

  static constraints = {
	 name blank:false
  }
  
}