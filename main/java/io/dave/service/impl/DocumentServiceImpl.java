package io.dave.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dave.entity.Document;
import io.dave.repo.DocumentRepository;
import io.dave.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;
	
	public void saveDocument(Document doc) {
		repo.save(doc);
	}

	public List<Object[]> getDocumentIdAndName() {
		return repo.getDocumentIdAndName();
	}
	
	public void deleteDocumentById(Long id) {
		if(repo.existsById(id))
			repo.deleteById(id);
		else 
			throw new RuntimeException("Document Not exist");
	}
	
	public Document getDocumentById(Long id) {
		return repo.findById(id).orElseThrow(
				()->new RuntimeException("Document Not exist")
				);
	}

}
