package com.example.service.service_4;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.example.entity.entity1.Like;
import com.example.repository.repository_4.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    LikeRepository lRepository;
    
    @Autowired 
    EntityManagerFactory emf;

    // @Override
    // public int deleteLikeBatch(Long[] lno) {
    //     EntityManager em = emf.createEntityManager();
    //     try{
    //         em.getTransaction().begin();

    //         for( Long tmp : lno) {
	// 			//기본키를 이용해서 기존 데이터를 꺼냄
    //             Like likeEntity = em.find(Like.class, tmp);
	// 			em.remove(likeEntity);
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

    @Override
    public int deleteLikeBatch(List<Like> list) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            for( Like like : list) {
                em.remove(like);
			}
            
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
    public int deleteLikeBatch1(Long[] lno) {
        EntityManager em = emf.createEntityManager();
             try{
                 em.getTransaction().begin();
    
                 for( Long tmp : lno) {
         			//기본키를 이용해서 기존 데이터를 꺼냄
                     Like likeEntity = em.find(Like.class, tmp);
         			em.remove(likeEntity);
         		}
                
                 em.getTransaction().commit();
                 return 1;
             }
             catch(Exception e){
                 e.printStackTrace();
                 em.getTransaction().rollback();
                 return 0;
             } 
    }
    
    
}
