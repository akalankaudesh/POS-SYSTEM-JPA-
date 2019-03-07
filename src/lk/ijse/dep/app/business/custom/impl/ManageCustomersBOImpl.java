package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageCustomersBO;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ManageCustomersBOImpl implements ManageCustomersBO {

    private CustomerDAO customerDAO;

    @Autowired
    public ManageCustomersBOImpl(CustomerDAO customerDAO) {
      this.customerDAO=customerDAO;

    }

    public List<CustomerDTO> getCustomers() throws Exception {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            customerDAO.setEntityManager(em);
//            em.getTransaction().begin();
            return customerDAO.findAll().map(Converter::<CustomerDTO>getDTOList).get();
//            em.getTransaction().commit();
//            return customerDTOS;
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            throw ex;
//        }

    }

    public void createCustomer(CustomerDTO dto) throws Exception {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            customerDAO.setEntityManager(em);
//            em.getTransaction().begin();
//            customerDAO.save(Converter.getEntity(dto));
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            throw ex;
//        }

        customerDAO.save(Converter.getEntity(dto));

    }

    public void updateCustomer(CustomerDTO dto) throws Exception {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            customerDAO.setEntityManager(em);
//            em.getTransaction().begin();
//            customerDAO.update(Converter.getEntity(dto));
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            throw ex;
//        }

        customerDAO.update(Converter.getEntity(dto));

    }

    public void deleteCustomer(String customerID) throws Exception {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            customerDAO.setEntityManager(em);
//            em.getTransaction().begin();
//            customerDAO.delete(customerID);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            throw ex;
//        }

        customerDAO.delete(customerID);

    }

    public CustomerDTO findCustomer(String id) throws Exception {
//        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
//        try {
//            customerDAO.setEntityManager(em);
//            em.getTransaction().begin();
//            CustomerDTO customerDTO = customerDAO.find(id).map(Converter::<CustomerDTO>getDTO).orElse(null);
//            em.getTransaction().commit();
//            return customerDTO;
//        } catch (Exception ex) {
//            em.getTransaction().rollback();
//            throw ex;
//        }

        return customerDAO.find(id).map(Converter::<CustomerDTO>getDTO).orElse(null);

    }

}
