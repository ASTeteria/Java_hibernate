package HW3;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HW3");

        try (
            EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();


            Car car1 = new Car();
            car1.setModel("Slavuta");
            car1.setCarType(Type.SEDAN);
            car1.setPower(120);
            car1.setPrice(1000);
            car1.setYear(2020);

            Car car2 = new Car();
            car2.setModel("Tavriya");
            car2.setCarType(Type.JEEP);
            car2.setPower(110);
            car2.setPrice(900);
            car2.setYear(2003);

            DriveLicense license = new DriveLicense();
            license.setSeries("AA1233456");


            Owner owner = new Owner();
            owner.setName("Sergey Petrov");
            owner.setDriveLicense(license);

            ArrayList<Car> cars = new ArrayList<>();
            cars.add(car1);
            cars.add(car2);
            owner.setCars(cars);


            em.persist(owner);


            em.getTransaction().commit();
            System.out.println("Дані збережено");


        }
        emf.close();
    }


    }