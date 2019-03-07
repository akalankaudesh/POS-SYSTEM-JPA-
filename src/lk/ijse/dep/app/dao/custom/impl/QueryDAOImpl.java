package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.dto.OrderDetailDTO;
import lk.ijse.dep.app.entity.CustomEntity;
import lk.ijse.dep.app.entity.Order;
import lk.ijse.dep.app.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public class QueryDAOImpl implements QueryDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<List<OrderDetail>> findOrderDetailsWithItemDescriptions(String orderId) throws Exception {
        String quary = "select o FROM  OrderDetail o Where o.orderId=' "+orderId+"'";
        List<OrderDetail> resultList = em.createQuery(quary, OrderDetail.class).getResultList();
        System.out.println(resultList);
        return Optional.ofNullable(resultList);

    }

    @Override
    public Optional<List<CustomEntity>> findAllOrdersWithCustomerNameAndTotal() throws Exception {

        String quary = "SELECT o.id,o.date,c.id,c.name,SUM(d.unitPrice * d.unitPrice) as total FROM Orders o LEFT JOIN Customer c on o.id = c.id LEFT JOIN OrderDetail  d on o.id = d.orderId group by o.id,o.date,c.id";

        List<Order> orders = em.createQuery(" select o FROM Order o", Order.class).getResultList();
        List<CustomEntity> ce = new ArrayList<>();
        for (Order o : orders) {
            List resultList = em.createQuery(" select sum (o.unitPrice * o.qty) FROM OrderDetail o WHERE o.orderId='" + o.getId() + "'GROUP BY o.orderId ").getResultList();

            System.out.println(resultList.get(0).toString());
            double i = Double.parseDouble(resultList.get(0).toString());

            ce.add(new CustomEntity(o.getId(), o.getDate(), o.getCustomer().getId(), o.getCustomer().getName()
                    , i));
        }


        System.out.println(orders);
        System.out.println(orders.get(0));

        return Optional.ofNullable(ce);

    }

    @Override
    public int count() throws Exception {
        System.out.println(em);
        Query query = em.createQuery("SELECT count(o.id) FROM Order o");
        List resultList = query.getResultList();

        System.out.println(resultList.get(0));

        return Integer.parseInt(resultList.get(0).toString());
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
