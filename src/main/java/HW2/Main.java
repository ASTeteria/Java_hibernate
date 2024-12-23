package HW2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Word.class)
                .addAnnotatedClass(Car.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Word word = new Word("Trulyalya");
        Word word1 = new Word("Truyampampam");
        Word word2 = new Word("Tralyalya");
        Word word3 = new Word("Trumpampam");

        List<Word> list = session.createQuery("select w from Word w", Word.class).list();
        System.out.println(list);

        Car car = new Car("Slavuta", BodyType.JEEP, 120, 2000, 2003);
        Car car2 = new Car("Tavriya", BodyType.COUPE, 110, 20002, 2005);
        Car car3 = new Car("Buhanka", BodyType.TRUCK, 150, 20089, 2011);
        List<Car> listCar = session.createQuery("select c from Car c", Car.class).list();
        System.out.println(listCar);


        session.save(word);
        session.save(word1);
        session.save(word2);
        session.save(word3);

        session.save(car);
        session.save(car2);
        session.save(car3);



        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}