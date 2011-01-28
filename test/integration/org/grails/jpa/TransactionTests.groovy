package org.grails.jpa

import org.acme.Person
import org.springframework.transaction.TransactionStatus

/**
 * @author Graeme Rocher
 * @since 1.1
 */

public class TransactionTests extends GroovyTestCase{
  	static transactional = true


    void testTransactionCommit() {
        // since HSQLDB is not transaction anything beyond the below
        // test is rather pointless
        Person.withTransaction {
            new Person(name:"Fred").save()
            new Person(name:"Bob").save()
                      
        }

        assertEquals 2, Person.count
    }

}