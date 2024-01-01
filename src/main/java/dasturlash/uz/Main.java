package dasturlash.uz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        insertStudents();
//        selectExample();
        pagination();
    }

    private static void selectExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        String sql = "from StudentEntity s where age > :studentAge ";
        Query<StudentEntity> query = session.createQuery(sql);
        query.setParameter("studentAge", 22);
        List<StudentEntity> studentList = query.list();

        for (StudentEntity entity : studentList) {
            System.out.println(entity);
        }

        factory.close();
        session.close();
    }

    private static void pagination() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        String sql = "from StudentEntity";
        Query<StudentEntity> query = session.createQuery(sql);
        query.setFirstResult(2);
        query.setMaxResults(30);

        List<StudentEntity> studentList = query.list();

        for (StudentEntity entity : studentList) {
            System.out.println(entity);
        }

        factory.close();
        session.close();
    }

    public static void insertStudents() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentEntity student1 = new StudentEntity();
        student1.setName("Ali");
        student1.setSurname("Aliyev");
        student1.setAge(22);
        student1.setGroupName("alfa");
        session.save(student1);

        StudentEntity student2 = new StudentEntity();
        student2.setName("Vali");
        student2.setSurname("Valiyev");
        student2.setAge(24);
        student2.setGroupName("betta");
        session.save(student2);

        StudentEntity student3 = new StudentEntity();
        student3.setName("Toshmat");
        student3.setSurname("Toshmatov");
        student3.setAge(19);
        student3.setGroupName("delta");
        session.save(student3);

        StudentEntity student4 = new StudentEntity();
        student4.setName("Eshmat");
        student4.setSurname("Eshmatov");
        student4.setAge(28);
        student4.setGroupName("Mazgi");
        session.save(student4);

        t.commit();

        factory.close();
        session.close();
    }
}