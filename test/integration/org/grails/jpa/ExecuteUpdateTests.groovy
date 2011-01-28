package org.grails.jpa

import org.acme.Person
import javax.persistence.EntityManager

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class ExecuteUpdateTests extends GroovyTestCase{

  static transactional = true

  void testExecuteUpdate() {
      new Person(name:"Bob").save()
      new Person(name:"Fred").save()

      assertEquals 2, Person.count

      Person.withEntityManager { EntityManager em -> em.clear() }

      def result = Person.executeUpdate("delete from ${Person.name}")

      assertEquals "two records should have been deleted", 2, result

      assertEquals 0, Person.count
  }

  void testExecuteUpdateWithOrdinals() {
      new Person(name:"Bob").save()
      new Person(name:"Fred").save()

      assertEquals 2, Person.count

      Person.withEntityManager { EntityManager em -> em.clear() }

      def result = Person.executeUpdate("delete from ${Person.name} as p where p.name = ?", ["Bob"])

      assertEquals "two records should have been deleted", 1, result

      assertEquals 1, Person.count
  }


  void testExecuteUpdateWithNamedParams() {
    new Person(name:"Bob").save()
    new Person(name:"Fred").save()

    assertEquals 2, Person.count

    Person.withEntityManager { EntityManager em -> em.clear() }

    def result = Person.executeUpdate("delete from ${Person.name} as p where p.name = :name", [name:"Bob"])

    assertEquals "two records should have been deleted", 1, result

    assertEquals 1, Person.count

  }
}