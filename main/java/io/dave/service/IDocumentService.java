package io.dave.service;

import java.util.List;

import io.dave.entity.Document;

public interface IDocumentService {

	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Long id);
	Document getDocumentById(Long id);
}
