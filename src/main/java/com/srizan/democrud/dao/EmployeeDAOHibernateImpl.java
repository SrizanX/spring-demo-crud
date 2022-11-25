package com.srizan.democrud.dao;

import com.srizan.democrud.entity.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    // define field for entity manager

    private EntityManager entityManager;

    // set up constructor
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);


        // create query

        Query<Employee> theQuery =  currentSession.createQuery("from Employee", Employee.class);

        List<Employee> employees = theQuery.getResultList();

        // Return
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Employee.class, theId);
    }

    @Override
    public void save(Employee theEmployee) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theEmployee);

    }

    @Override
    public void deleteById(int theId) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession
                .createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId",theId);

        theQuery.executeUpdate();
    }
}
