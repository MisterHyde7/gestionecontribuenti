package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteService {
	
	public List<Contribuente> listAllElements();

	public Contribuente caricaSingoloElemento(Long id);

	public Contribuente caricaSingoloElementoEager(Long id);

	public void aggiorna(Contribuente contribuenteInstance);

	public void inserisciNuovo(Contribuente contribuenteInstance);

	public void rimuovi(Contribuente contribuenteInstance);

	Page<Contribuente> findByExampleWithPagination(Contribuente example, Integer pageNo, Integer pageSize,
			String sortBy);

	public List<Contribuente> cercaByCognomeENomeILike(String term);
	
	public int dammiSommaImportiCartelle(Long id);
	
	public int dammiSommaImportiCartelleConcluse(Long id);
	
	public int dammiSommaImportiCartelleInContenzioso(Long id);

}
