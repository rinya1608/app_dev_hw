package ru.hibernate.yarullin.homework.entity.listeners;

import jakarta.persistence.PreRemove;
import ru.hibernate.yarullin.homework.entity.Student;

public class AuditStudentListener {
    @PreRemove
    private void beforeRemove(Student student){
        System.out.println(student.toString());
    }
}
