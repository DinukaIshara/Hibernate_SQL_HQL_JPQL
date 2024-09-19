package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query quary = session.createQuery("from Customer");
        List<Customer> customers = quary.list();

        for (Customer customer: customers){
            System.out.println(customer.getCid());
        }

        //Unique One data from customer
        /*int id = 1;
        Query quary2 = session.createQuery("from Customer where cid = :cid");
        quary2.setParameter("cid", id);*/

        Query quary2 = session.createQuery("from Customer where cid = ?1");
        quary2.setParameter(1,1);

        Customer customer = (Customer) quary2.uniqueResult();

        System.out.println(customer);
        System.out.println(customer.getCid());

        System.out.println(customer.getAddresses());

        //Load only one column
        Query quary3 = session.createQuery("select cid from Customer where cid = ?1");
        quary3.setParameter(1,1);

        Customer customer3 = (Customer) quary2.uniqueResult();

        System.out.println(customer3);
        System.out.println(customer3.getCid());

        System.out.println(customer3.getAddresses());

        //
        Query query4 = session.createQuery("select cid,name from Customer where name = ?1");
        query4.setParameter(1,1);

        Object[] objects = (Object[]) query4.uniqueResult();
        System.out.println(Arrays.toString(objects));

        //----------------Assignment 02---------------------------------------------------------------------------

        //----------------Insert--------------------
        Query insert = session.createQuery("insert into Customer(cid,name) values(?1,?2)");
        insert.setParameter(1,5);
        insert.setParameter(2,"Anu");
        insert.executeUpdate();

        //---------------Update--------------------------
        /*Query update = session.createQuery("update Customer set name=?1 where cid=?2");
        update.setParameter(1,"Janaka");
        update.setParameter(2,4);
        update.executeUpdate();*/

        //----------------Delete----------------------------
        /*Query delete = session.createQuery("delete from Customer where cid=?1");
        delete.setParameter(1,5);
        delete.executeUpdate();*/

        //----------------Query query = session.createQuery("select cid,name from Customer where cid=?1");------------------
        /*Query query5 =session.createQuery("select cid,name from Customer where cid=?1");
        query5.setParameter(1, 1);
        Object[] customer = (Object[]) query5.uniqueResult();
        for(Object o : customer){
            System.out.println(o);
        }*/


        //------------Query query6 = session.createQuery("select cid, name from Customer");-----------------------
        /* Query query6 = session.createQuery("select cid, name from Customer");
        List<Object[]>customers = query6.list();
        for (Object[] objects : customers) {
            System.out.println(Arrays.toString(objects));
        }*/

        //------------------------Query query=session.createQuery("select a.aid,a.road,c.name from Address a inner join Customer c on a.customer c");--------------------
        Query query7 =session.createQuery("select a.aid,a.road,c.name from Address a inner join Customer c on a.customer c");
        List<Object[]>customerList = query7.list();
        for (Object[] object : customerList) {
            System.out.println(Arrays.toString(object));
        }

        transaction.commit();
        session.close();
    }
}