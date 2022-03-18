package it.prova.gestionecontribuenti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cartellaesattoriale")
public class CartellaEsattoriale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "importo")
	private Integer importo;

	@Column(name = "stato")
	private Stato stato;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private Contribuente contribuente;

	public CartellaEsattoriale() {
		super();
	}

	public CartellaEsattoriale(String descrizione, Integer importo, Stato stato) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}

	public CartellaEsattoriale(Long id, String descrizione, Integer importo, Contribuente contribuente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale(Long id, String descrizione, Integer importo, Stato stato, Contribuente contribuente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Contribuente getContribuente() {
		return contribuente;
	}

	public void setContribuente(Contribuente contribuente) {
		this.contribuente = contribuente;
	}

}