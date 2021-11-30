package com.softtech.app.service.implemetation;

import com.softtech.app.model.StudentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Created by N.Bimeri on 17/09/2021.
 */
@Repository
@Transactional
public class StudentDoaService {
    private EntityManager entityManager;
     Logger logger = LoggerFactory.getLogger(StudentDoaService.class);

    public StudentEntity addStudent(StudentEntity studentEntity){
    logger.info("student detail: "+ studentEntity);
        if (studentEntity == null){
            throw new IllegalArgumentException();
        }
        entityManager.persist(studentEntity);
        return studentEntity;
    }
}
