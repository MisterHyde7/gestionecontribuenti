package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.repository.cartella.CartellaRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {

	@Autowired
	private CartellaRepository repository;

	@Override
	public List<CartellaEsattoriale> listAllElements() {
		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEsattorialeEager(id);
	}

	@Override
	public void aggiorna(CartellaEsattoriale cartellaInstance) {
		repository.save(cartellaInstance);
	}

	@Override
	public void inserisciNuovo(CartellaEsattoriale cartellaInstance) {
		repository.save(cartellaInstance);
	}

	@Override
	public void rimuovi(CartellaEsattoriale cartellaInstance) {
		repository.delete(cartellaInstance);
	}

}
