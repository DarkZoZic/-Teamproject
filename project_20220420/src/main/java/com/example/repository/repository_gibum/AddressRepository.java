package com.example.repository.repository_gibum;

import java.util.List;

import com.example.entity.entity2.Address;
import com.example.entity.entity2.Club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository 
    extends JpaRepository<Address, Long>{
        
        @Query(value = "SELECT * FROM"+
        " ADDRESSTBL WHERE A1=:address", nativeQuery = true)
        public List<Address> addressesList1(
                @Param(value="address") String address);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTBL WHERE A2=:address", nativeQuery = true)
        public List<Address> addressesList2(
                @Param(value="address") String address);

        @Query(value = "SELECT * FROM"+
        " ADDRESSTBL A, CLUB C WHERE C.A_CODE=:address", nativeQuery = true)
        public List<Address> addressesClubList(
                @Param(value="address") String address);


        Address findByaCode(Long address);

        List<Address> findBya2(String address);

        
}
