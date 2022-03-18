package it.prova.gestionecontribuenti.service;

import java.util.List;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {
	
	public List<CartellaEsattoriale> listAllElements();

	public CartellaEsattoriale caricaSingoloElemento(Long id);

	public CartellaEsattoriale caricaSingoloElementoEager(Long id);

	public void aggiorna(CartellaEsattoriale cartellaInstance);

	public void inserisciNuovo(CartellaEsattoriale cartellaInstance);

	public void rimuovi(CartellaEsattoriale cartellaInstance);

}
