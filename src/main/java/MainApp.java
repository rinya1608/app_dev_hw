import jakarta.persistence.EntityManager;
import ru.hibernate.yarullin.homework.dao.PersonDAO;
import ru.hibernate.yarullin.homework.dao.RecordBookDAO;
import ru.hibernate.yarullin.homework.dao.StudentDAO;
import ru.hibernate.yarullin.homework.entity.Person;
import ru.hibernate.yarullin.homework.entity.RecordBook;
import ru.hibernate.yarullin.homework.entity.Student;
import ru.hibernate.yarullin.homework.utils.HibernateSessionFactory;
import ru.hibernate.yarullin.homework.utils.RandomGeneratorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static ru.hibernate.yarullin.homework.utils.RandomGeneratorUtils.*;

public class MainApp {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateSessionFactory.getSessionFactory().createEntityManager();
        PersonDAO personDAO = new PersonDAO(entityManager);
        RecordBookDAO recordBookDAO = new RecordBookDAO(entityManager);
        StudentDAO studentDAO = new StudentDAO(entityManager);

        entityManager.getTransaction().begin();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Козлов", "Валерий", "Андреевич"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Ослов", "Никита", "Михайлович"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Черепахов", "Михаил", "Денисович"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Маслов", "Антон", "Андреевич"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Фахитова", "Анна", "Андреева"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Курлов", "Мамед", "Алексеевич"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Мурлова", "Виктория", "Денисовна"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Орлов", "Степан", "Михайлович"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Самолетов", "Андрей", "Мамедович"));
        persons.add(new Person(getRandomPassportSeries(), getRandomPassportNumber(), "Кумаков", "Мухтар", "Михайловна"));

        persons.forEach(personDAO::save);

        List<RecordBook> recordBooks = new ArrayList<>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 9); i++) {
            RecordBook recordBook = new RecordBook(getRandomCode());
            recordBooks.add(recordBook);
            recordBookDAO.save(recordBook);
        }

        for (int i = 0; i < persons.size(); i++) {
            Student student = new Student();
            student.setPerson(persons.get(i));
            student.setGroup(RandomGeneratorUtils.getRandomGroup());
            if (i < recordBooks.size()) {
                student.setRecordBook(recordBooks.get(i));
            }
            studentDAO.save(student);
        }

        entityManager.getTransaction().commit();


        System.out.println("------------5.4 HQL------------");
        studentDAO.findByFioLikeWithHQL("%а%").forEach(System.out::println);
        System.out.println("------------5.4 Criteria------------");
        studentDAO.findByFioLikeWithCriteria("%а%").forEach(System.out::println);

        System.out.println("------------5.5 HQL------------");
        studentDAO.findByRecordBookNotNullWithHQL().forEach(System.out::println);
        System.out.println("------------5.5 Criteria------------");
        studentDAO.findByRecordBookNotNullWithCriteria().forEach(System.out::println);

        entityManager.close();
    }
}
