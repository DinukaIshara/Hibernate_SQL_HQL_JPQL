package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        //----------------Assignment 02---------------------------------------------------------------------------

        //--------------------Insert-----------------------------------
        /*No insert query in jpql have to use save method
        Customer customer = new Customer();
        customer.setCid(cid);
        customer.setName(name);*/


        //--------------------Update----------------------------------------
        /*Query update = session.createQuery("update Customer c set c.name = :name where c.id = :id");
        update.setParameter("name", "Rasindu");
        update.setParameter("id", 1);
        update.executeUpdate();*/


        //-----------------------Delete------------------------
        /*Query delete = session.createQuery("delete from Customer c where c.cid = :id");
        delete.setParameter("id", 6);
        delete.executeUpdate();*/


        //----------------Search------------------------------
        /*Query query = session.createQuery("select c.name from Customer c");
        List<String> customers = query.list();
        for (String customer : customers) {
            System.out.println(customer);
        }*/


        //----------------Join--------------------------------
        Query query1 = session.createQuery("select c.id,c.name,a.city,a.road from Address a join a.customer c");
        List<Object[]> customers = query1.list();
        for (Object[] customer : customers) {
            System.out.println(Arrays.toString(customer));
        }

        transaction.commit();
        session.close();
    }
}