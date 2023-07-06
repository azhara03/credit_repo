package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    /*private static EntityManagerFactory factory = null;
    private static EntityManager entityManager = null;*/
    //@BeforeClass
    /*public static void init() {
        factory = Persistence.createEntityManagerFactory("jpa-db");
        entityManager = factory.createEntityManager();
    }*/
    @Autowired
    private ScheduleRepository scheduleRepository;
    @PersistenceContext
    private EntityManager em;

    public List<Schedule> findAll() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @Override
    public Schedule getById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }
    @Override
    public Schedule findById(Integer id) {
        return scheduleRepository.findById(id).orElse(null);
    }
    @Override
    public List<Schedule> getSchedule(Integer creditId) {
        return scheduleRepository.getSchedule(creditId);
    }
    /*@Override
    public List<Schedule> getSchedule(Integer creditId) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("firstProcedure");
        query.setParameter("id", creditId);
        query.execute();

    }*/

}
