beans = {

  pum(org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager) {
    persistenceXmlLocation = "file:./test/conf/persistence.xml"
    defaultDataSource = ref("dataSource")
  }
  entityManagerFactory(org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean) {
    persistenceUnitManager = ref("pum")
    
    beanClassLoader = ref("classLoader")
  }

  transactionManager(org.springframework.orm.jpa.JpaTransactionManager) {
    entityManagerFactory = entityManagerFactory
  }
}