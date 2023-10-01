package edu.ufape.lmts.listalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ufape.lmts.listalk.model.MyList;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Long> {
	List<MyList> findByuserId(String userId);

}