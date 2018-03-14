package com.uniovi.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR"
			+ "	LOWER(r.name) LIKE LOWER(?1))")
	Page<User> searchUsersByEmailAndName(Pageable pageable, String seachtext);

	User findByEmail(String email);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.email) LIKE LOWER(?1))")
	Page<User> searchByNameOrEmail(Pageable pageable, String searchText);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET recibida = ?1 WHERE id = ?2")
	void updateResend(Boolean resend, Long id);

}
