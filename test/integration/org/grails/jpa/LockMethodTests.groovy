package org.grails.jpa

import org.acme.Person

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class LockMethodTests extends GroovyTestCase{

  static transactional = true

  void testLockInstanceMethod() {
      def person = new Person(name:"Fred")

      person.save()

      assertNotNull "identifier should exist", person.id

      // just testing the methods are actually callable here
      // the in-memory db doesn't support "SELECT..FOR UPDATE"
      // so any other test is pretty pointless
      person.lock()

      person = Person.lock(person.id)

      assertNotNull "should not be null", person
  }

}