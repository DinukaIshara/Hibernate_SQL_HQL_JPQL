package lk.ijse;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        //insert,update,delete,searchById

        //---------------------Search--------------------------
        NativeQuery quary = session.createNativeQuery("select * from customer");
        quary.addEntity(Customer.class);
        List<Customer> customers = quary.list();

        for (Customer customer: customers){
            System.out.println(customer.getCid());
        }

        //----------------Assignment 02---------------------------------------------------------------------------

        //----------------------------Insert------------------------------
        /*NativeQuery insert = session.createNativeQuery("insert into customer values(?1, ?2)");
        insert.setParameter(1,2);
        insert.setParameter(2,"Lasith");
        insert.executeUpdate();*/

        //----------------------------Update------------------------
        NativeQuery update = session.createNativeQuery("update customer set name = ?1 where cid = ?2");
        update.setParameter(1,"Dinuka");
        update.setParameter(2,2);
        update.executeUpdate();

        //------------------------Delete-------------------------------------
        /*NativeQuery delete = session.createNativeQuery("delete from customer where cid = ?1");
        delete.setParameter(1,2);
        delete.executeUpdate();*/


        //-----------------------SearchByName------------------------------
        NativeQuery query4 = session.createNativeQuery("select * from customer where name = ?1");
        query4.addEntity(Customer.class);
        query4.setParameter(1,"Dinuka");
        Customer customer = (Customer) query4.uniqueResult();
        System.out.println(customer);


        transaction.commit();
        session.close();
    }
}