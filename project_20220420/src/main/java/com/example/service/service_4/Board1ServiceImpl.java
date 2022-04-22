package com.example.service.service_4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.example.entity.entity2.Board1;
import com.example.repository.repository_4.Board1Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Board1ServiceImpl implements Board1Service{

    @Autowired 
    Board1Repository b1Repository;

    @Autowired 
    EntityManagerFactory emf;


    @Override
    public int insertBoard1One(Board1 board1) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Board1 insertBoard = new Board1();
            insertBoard.setBTitle(board1.getBTitle());
            insertBoard.setBContent(board1.getBContent());

            em.persist(insertBoard);           

            em.getTransaction().commit();
            return 1;

        }
        catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;

        }
    }

    @Override
    public int updateBoard1One(Board1 board1) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteBoard1One(Long bNo) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            em.remove(bNo);
            
            em.getTransaction().commit();
            return 1;
        }
        catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    @Override
    public int deleteBoard1Batch(Long[] bNo) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
