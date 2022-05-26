package ru.hibernate.yarullin.homework.entity.metamodel;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.hibernate.yarullin.homework.entity.RecordBook;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecordBook.class)
public abstract class RecordBook_ {

	public static volatile SingularAttribute<RecordBook, Integer> code;
	public static volatile SingularAttribute<RecordBook, Long> id;

	public static final String CODE = "code";
	public static final String ID = "id";

}

