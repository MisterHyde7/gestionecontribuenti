package it.prova.gestionecontribuenti.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public class CartellaEsattorialeDTO {

	private Long id;

	@NotBlank(message = "{descrizione.notblank}")
	@Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String descrizione;

	@NotNull(message = "{importo.notnull}")
	@Min(1)
	private Integer importo;

	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;

	public CartellaEsattorialeDTO() {
		super();
	}

	public CartellaEsattorialeDTO(
			@NotBlank(message = "{descrizione.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String descrizione,
			@NotNull(message = "{importo.notnull}") @Min(1) Integer importo,
			@NotNull(message = "{contribuente.notnull}") ContribuenteDTO contribuente) {
		super();
		this.descrizione = descrizione;
		this.importo = importo;
		this.contribuente = contribuente;
	}

	public CartellaEsattorialeDTO(Long id,
			@NotBlank(message = "{descrizione.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String descrizione,
			@NotNull(message = "{importo.notnull}") @Min(1) Integer importo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
	}

	public CartellaEsattorialeDTO(Long id,
			@NotBlank(message = "{descrizione.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String descrizione,
			@NotNull(message = "{importo.notnull}") @Min(1) Integer importo,
			@NotNull(message = "{contribuente.notnull}") ContribuenteDTO contribuente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
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

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}

	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		return new CartellaEsattoriale(this.id, this.descrizione, this.importo,
				this.contribuente.buildContribuenteModel());
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(CartellaEsattoriale cartellaModel) {
		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaModel.getId(),
				cartellaModel.getDescrizione(), cartellaModel.getImporto());

		result.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(cartellaModel.getContribuente()));

		return result;
	}

	public static List<CartellaEsattorialeDTO> createCartellaDTOListFromModelList(
			List<CartellaEsattoriale> modelListInput) {
		return modelListInput.stream().map(filmEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(filmEntity);
		}).collect(Collectors.toList());
	}
}
