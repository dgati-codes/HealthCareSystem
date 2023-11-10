package io.dave.repo;

import java.util.List;

import io.dave.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentRepository 
	extends JpaRepository<Document, Long>
{

	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();

}
