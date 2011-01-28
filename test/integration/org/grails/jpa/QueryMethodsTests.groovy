package org.grails.jpa

import org.acme.Person

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class QueryMethodsTests extends GroovyTestCase {

  static transactional = true

  void testExecuteQueryMethod() {
    def p = new Person(name:"Fred")
    p.save()


    def results = Person.executeQuery("select p.name from Person p")

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0]
  }

  void testExecuteQueryWithOrdinals() {
    def p = new Person(name:"Fred")
    p.save()


    def results = Person.executeQuery("select p.name from Person p where p.name = ?", ["Fred"])

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0]
  }

  void testExecuteQueryWithNamedParams() {
    def p = new Person(name:"Fred")
    p.save()


    def results = Person.executeQuery("select p.name from Person p where p.name = :myName", [myName:"Fred"])

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0]

  }

  void testFindAllMethod() {
    def p = new Person(name:"Fred")
    p.save()


    def results = Person.findAll("select p from Person p")

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0].name
  }

  void testFindAllWithOrdinals() {
    def p = new Person(name:"Fred")
    p.save()


        def results = Person.findAll("select p from Person p where p.name = ?", ["Fred"])

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0].name
  }

  void testFindAllWithNamedParams() {
    def p = new Person(name:"Fred")
    p.save()


    def results = Person.findAll("select p from Person p where p.name = :myName", [myName:"Fred"])

    assertTrue "should have got results", results.size()>0
    assertEquals "first result should have been Fred","Fred", results[0].name

  }

 void testFindMethod() {
    def p = new Person(name:"Fred")
    p.save()


    def person = Person.find("select p from Person p")

    assertNotNull "should have got results", person
    assertEquals "first result should have been Fred","Fred", person.name
  }

  void testFindWithOrdinals() {
    def p = new Person(name:"Fred")
    p.save()

    assertNull "should have returned null", Person.find("select p from Person p where p.name = ?", ["Bob"])
    
    def person = Person.find("select p from Person p where p.name = ?", ["Fred"])

    assertNotNull "should have got results", person
    assertEquals "first result should have been Fred","Fred", person.name
  }

  void testFindWithNamedParams() {
    def p = new Person(name:"Fred")
    p.save()


    assertNull "should have returned null", Person.find("select p from Person p where p.name = :myName", [myName:"Bob"])
    
    def person = Person.find("select p from Person p where p.name = :myName", [myName:"Fred"])

    assertNotNull "should have got results", person
    assertEquals "first result should have been Fred","Fred", person.name

  }

  void testFindWhere() {
    def p = new Person(name:"Fred", age:10)
    p.save()


    assertNull "should have returned null", Person.findWhere(name:"Bob")

    def person = Person.findWhere(name:"Fred", age:10)

    assertNotNull "should have got results", person
    assertEquals "first result should have been Fred","Fred", person.name

  }


  void testGetAll() {
    def p1 = new Person(name:"Fred", age:10)
    p1.save()

    def p2 = new Person(name:"Bob", age:11)
    p2.save()


    List results = Person.getAll([p1.id, p2.id])

    assertNotNull "should have returned a list", results
    assertEquals "should have returned 2 results", 2, results.size()
    assertNotNull "should have contained entity",results.find { it.id == p1.id }  

  }
}