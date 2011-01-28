package org.grails.jpa

import org.acme.Person

/**
 * @author Graeme Rocher
 * @since 1.0
 * 
 * Created: May 14, 2009
 */

public class BasicPersistenceMethodsTests extends GroovyTestCase{

    static transactional = true
  

    void testValidation() {
      def p = new Person(name:"")
      assertNull "should not have validated",  p.save(flush:true )
    }
    void testSaveAndGet() {

          def p = new Person(name:"Fred")
          p.save(flush:true )


          p = Person.get(p.id)

          assertNotNull "should have retrieved a person", p

          // test get(..) with a string for type conversion
          p = Person.get(p.id.toString())

          assertNotNull "should have retrieved a person", p
    }

    void testExists() {
      def p = new Person(name:"Fred")
      p.save()

      assertTrue "should exist", Person.exists(p.id)
      assertFalse "should not exist", Person.exists(34897)
    }
  
    void testCount() {
      def p = new Person(name:"Fred")
      p.save()
      p = new Person(name:"Barney")
      p.save()

      assertEquals 2, Person.count()
      assertEquals 2, Person.count
    }

    void testDelete() {
      def p = new Person(name:"Fred")
      p.save()

      p.delete(flush:true)


      assertNull Person.get(p.id)

    }

    void testListMethod() {
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Bob").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Amy").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()
      new Person(name:"Fred").save()




      def results = Person.list()

      assertEquals 12, results.size()

      assertEquals 5, Person.list(max:5).size()


      results = Person.list(sort:"name")
      assertEquals "Amy", results[0].name
      assertEquals "Bob", results[1].name
      assertEquals "Fred", results[2].name


      results = Person.list(sort:"name", order:"desc")
      assertEquals "Amy", results[-1].name
      assertEquals "Bob", results[-2].name
      assertEquals "Fred", results[-3].name

    }

}