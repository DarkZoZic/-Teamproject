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

    // controller-> service + serviceimpl -> repository
    // 컨트롤러는 서비스를 호출하는거고, 원래는 다이렉트로 호출하면 안됨. 
    // 여러 사람이 할 때는 service가 들어가야 함. 그래야 일들이 명시가 됨

    @Override
    public int insertBoard1One(Board1 board1) {
        EntityManager em = emf.createEntityManager();
        try{
            // 저장소 호출해서 만든다는데?
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

    // @Override
    // public int updateBoard1One(Board1 board1) {
    //     EntityManager em = emf.createEntityManager();
    //     try{
    //         em.getTransaction().begin();

    //         Board1 updateboard1 = new Board1();
    //         Board1 oldBoard = em.find(Board1.class, board1.getBNo());

    //         // 변경할 항목만 set
    //         oldBoard.setBTitle( updateboard1.getBTitle() );
    //         oldBoard.setBContent( updateboard1.getBContent() );
    //         oldBoard.setBImageList( updateboard1.getBImageList() ); // 이미지 이렇게 해도 되나?
    //         em.persist(oldBoard);
            
    //         em.getTransaction().commit();

    //         return 1;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         em.getTransaction().rollback();
    //         return 0;
    //     }
    // }

    // @Override
    // public int deleteBoard1One(Long bNo) {
    //     EntityManager em = emf.createEntityManager();
    //     try{
    //         em.getTransaction().begin();

    //         em.remove(bNo);
            
    //         em.getTransaction().commit();
    //         return 1;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         em.getTransaction().rollback();
    //         return 0;
    //     }
    // }

    // @Override
    // public int deleteBoard1Batch(Long[] bNo) {
    //     EntityManager em = emf.createEntityManager();
    //     try{
    //         em.getTransaction().begin();

    //         for( Long tmp : bNo) {
	// 			//기본키를 이용해서 기존 데이터를 꺼냄
	// 			Board1 oldBoard 
	// 				= em.find(Board1.class, tmp);
	// 			em.remove(oldBoard);
	// 		}
            
    //         em.getTransaction().commit();
    //         return 1;
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //         em.getTransaction().rollback();
    //         return 0;
    //     }
    // }

    // @Override
    // public int updateBoard1HitOne(Long bNo) {
    //     // TODO Auto-generated method stub
    //     return 0;
    // }

    // @Override
    // public Board1 selectBoard1One(Long bNo) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }








    
}
