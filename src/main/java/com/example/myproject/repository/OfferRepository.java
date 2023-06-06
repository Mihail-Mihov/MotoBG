package com.example.myproject.repository;

import com.example.myproject.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

        @Query(value = "SELECT * FROM offers o WHERE o.author_id = ?1", nativeQuery = true)
        List<OfferEntity> getAllByAuthor(Long authorId);
        @Query(value = "SELECT * FROM offers o WHERE UPPER(o.name) like ?1", nativeQuery = true)
        List<OfferEntity> getAllByName(String name);

        @Query(value = "SELECT * FROM offers", nativeQuery = true)
        List<OfferEntity> findAllOffers();

        @Modifying
        @Query(value = "DELETE * FROM offers o WHERE o.id = ?1", nativeQuery = true)
        void deleteById(Long id);
}
