package org.grails.jpa

import org.acme.Person

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class DynamicFindersTests extends GroovyTestCase{

  static transactional = true

  protected void setUp() {
    super.setUp();
    new Person(name:"Bob", age:11).save()
    new Person(name:"Fred", age:14).save()
    new Person(name:"Joe", age:9).save()
    new Person(name:"Ed", age:22).save()
    new Person(name:"Frank", age:3).save()
    new Person(name:"Ricky", age:11).save()
    new Person(name:"Tobby").save()

  }

  void testFinderWithGStringValue() {
    def name = "Bob"
    def person = Person.findByName("$name")

    assertNotNull "should have returned a person", person

    // call it again to exercise cached version
    person = Person.findByName("$name")

    assertNotNull "should have returned a person", person

  }

  void testSimpleDynamicFinder() {
      def person = Person.findByName("Bob")

      assertNotNull "should have returned a person", person

      // call it again to exercise cached version
      person = Person.findByName("Bob", [max:1])

      assertNotNull "should have returned a person", person

      assertNull "should not have returned a result", Person.findByName("Bad")
  }

  void testFinderWithAnd() {
     def person = Person.findByNameAndAge("Bob", 11)

      assertNotNull "should have returned a person", person

      // call it again to exercise cached version
      person = Person.findByNameAndAge("Bob", 11)
      assertNotNull "should have returned a person", person


      assertNull "should not have returned a result", Person.findByNameAndAge("Bob", 12)
  }

  void testFindWithLikeExpression() {
      def person = Person.findByNameLike("Bo%")

      assertNotNull "should have returned a person", person

      // call it again to exercise cached version
      person = Person.findByNameLike("Bo%")
      assertNotNull "should have returned a person", person


      assertNull "should not have returned a result", Person.findByNameLike("Ra%")

  }

  void testFindAllWithLikeExpression() {
    def people = Person.findAllByNameLike("Fr%")

    assertNotNull "should have returned a list", people
    assertEquals 2, people.size()

    // call it again to exercise cached version

    people = Person.findAllByNameLike("Fr%")

    assertNotNull "should have returned a list", people
    assertEquals 2, people.size()

    assertEquals "should have contained Frank", "Frank", people.find { it.name == "Frank" }?.name
  }


  void testFindAllWithOr() {
      def people = Person.findAllByNameOrAge("Fred", 11)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 3 results",3, people.size()

      // call it again to exercise cached version
      people = Person.findAllByNameOrAge("Fred", 11)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 3 results",3, people.size()

  }

  void testFindAllWithBetween() {
      def people = Person.findAllByAgeBetween(10,15)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 3 results",3, people.size()

      // call it again to exercise cached version
      people = Person.findAllByAgeBetween(10,15)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 3 results",3, people.size()

  }

  void testFindAllWithGreaterThan() {
      def people = Person.findAllByAgeGreaterThan(14)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 1 results",1, people.size()

      // call it again to exercise cached version
      people = Person.findAllByAgeGreaterThan(14)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 1 results",1, people.size()

      assertEquals "Ed", people[0].name

  }


  void testFindAllWithGreaterThanEquals() {
      def people = Person.findAllByAgeGreaterThanEquals(14)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 2 results",2, people.size()

      // call it again to exercise cached version
      people = Person.findAllByAgeGreaterThanEquals(14)

      assertNotNull "should have returned a list", people
      assertEquals "should have returned 2 results",2, people.size()


  }

}