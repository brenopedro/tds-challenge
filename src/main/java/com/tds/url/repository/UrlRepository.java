package com.tds.url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tds.url.model.UrlModel;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {
	
	Optional<UrlModel> findByUrl(String url);
	Optional<UrlModel> findByShortened(String shortened);

}
