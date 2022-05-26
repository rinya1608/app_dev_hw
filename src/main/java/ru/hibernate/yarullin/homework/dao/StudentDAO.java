package ru.hibernate.yarullin.homework.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import ru.hibernate.yarullin.homework.entity.Person;
import ru.hibernate.yarullin.homework.entity.Student;
import ru.hibernate.yarullin.homework.entity.metamodel.Person_;
import ru.hibernate.yarullin.homework.entity.metamodel.Student_;

import java.util.List;

public class StudentDAO {
    private EntityManager entityManager;

    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Student student) {
        entityManager.persist(student);
    }

    public void delete(Student student) {
        entityManager.remove(student);
    }

    public List<Student> findAll() {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> findByFioLikeWithHQL(String pattern) {
        return entityManager.createQuery("""
                select s
                from Student s
                join s.person p
                where p.firstName like :pattern or p.lastName like :pattern or p.middleName like :pattern
                """, Student.class).setParameter("pattern", pattern).getResultList();
    }

    public List<Student> findByFioLikeWithCriteria(String pattern) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, Person> join = root.join(Student_.PERSON);
        query.select(root)
                .where(criteriaBuilder
                        .or(criteriaBuilder.like(join.get(Person_.FIRST_NAME), pattern),
                                criteriaBuilder.like(join.get(Person_.LAST_NAME), pattern),
                                criteriaBuilder.like(join.get(Person_.MIDDLE_NAME), pattern)));
        return entityManager
                .createQuery(query)
                .getResultList();
    }

    public List<Student> findByRecordBookNotNullWithHQL() {
        return entityManager.createQuery("""
                        select s
                        from Student s
                        where s.recordBook is not null
                        """, Student.class)
                .getResultList();
    }

    public List<Student> findByRecordBookNotNullWithCriteria() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root)
                .where(criteriaBuilder.isNotNull(root.get(Student_.RECORD_BOOK)));
        return entityManager
                .createQuery(query)
                .getResultList();
    }
}
