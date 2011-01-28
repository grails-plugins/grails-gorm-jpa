package org.grails.jpa

import org.acme.Person
import javax.persistence.EntityManager

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class MergeMethodTests extends GroovyTestCase{
  static transactional = true

  void testMergeNewEntity() {
    def person = new Person(name:"Fred")

    assertNotNull "should have merged object",person.merge(flush:true)

    assertEquals 1, Person.count
  }

  void testMergeDetachedEntity() {
    def person = new Person(name:"Fred")

    assertNotNull "should have merged object",person.save()

    assertEquals 1, Person.count

    Person.withEntityManager { EntityManager em -> em.clear() }

    person.name = "Bob"
    person.merge(flush:true)

    assertEquals 1, Person.count

    Person.withEntityManager { EntityManager em -> em.clear() }
        

    person = Person.get(person.id)
    assertEquals "person name should have been updated by merge","Bob", person.name
  }

}