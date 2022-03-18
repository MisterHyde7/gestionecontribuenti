package it.prova.gestionecontribuenti.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.Contribuente;

public class ContribuenteDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceFiscale;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;

	// la proprieta uno a molti e cioe 'films' non serve al momento nelle view
	// e quindi non la mettiamo anche perche in genere risulta di difficile gestione

	public ContribuenteDTO() {
	}

	public ContribuenteDTO(Long id) {
		this.id = id;
	}

	public ContribuenteDTO(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
	}

	public ContribuenteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.dataDiNascita = dataDiNascita;
	}

	public ContribuenteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			@NotBlank(message = "{indirizzo.notblank}") String indirizzo,
			@NotNull(message = "{dataDiNascita.notnull}") Date dataDiNascita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.dataDiNascita = dataDiNascita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.indirizzo, this.codiceFiscale, this.dataDiNascita);
	}

	public static ContribuenteDTO buildContribuenteDTOFromModel(Contribuente contribuenteModel) {
		return new ContribuenteDTO(contribuenteModel.getId(), contribuenteModel.getNome(), contribuenteModel.getCognome(),
				contribuenteModel.getIndirizzo(), contribuenteModel.getCodiceFiscale(), contribuenteModel.getDataDiNascita());
	}

	public static List<ContribuenteDTO> createRegistaDTOListFromModelList(List<Contribuente> modelListInput) {
		return modelListInput.stream().map(contribuenteEntity -> {
			return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteEntity);
		}).collect(Collectors.toList());
	}

}
