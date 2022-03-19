package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecontribuenti.dto.CartellaEsattorialeDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.service.CartellaEsattorialeService;
import it.prova.gestionecontribuenti.service.ContribuenteService;

@Controller
@RequestMapping(value = "/cartellaEsattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public ModelAndView listAllCartelle() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_list_attribute", CartellaEsattorialeDTO.createCartellaDTOListFromModelList(cartelle));
		mv.setViewName("cartella/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createCartella(Model model) {
		model.addAttribute("insert_cartella_attr", new CartellaEsattorialeDTO());
		return "cartella/insert";
	}

	// inietto la request perché ci potrebbe tornare utile per ispezionare i
	// parametri
	@PostMapping("/save")
	public String saveCartella(
			@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per
		// visualizzarne nome e cognome nel campo testo
		if (cartellaEsattorialeDTO.getContribuente() == null
				|| cartellaEsattorialeDTO.getContribuente().getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaEsattorialeDTO.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(
					contribuenteService.caricaSingoloElemento(cartellaEsattorialeDTO.getContribuente().getId())));

		if (result.hasErrors()) {
			return "cartella/insert";
		}
		cartellaEsattorialeService.inserisciNuovo(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaEsattoriale";
	}

	@GetMapping("/search")
	public String searchCartella(Model model) {
		model.addAttribute("registi_list_attribute",
				ContribuenteDTO.createRegistaDTOListFromModelList(contribuenteService.listAllElements()));
		return "cartella/search";
	}

	@PostMapping("/list")
	public String listCartelle(CartellaEsattorialeDTO cartellaExample, ModelMap model) {

		model.addAttribute("cartella_list_attribute", cartellaEsattorialeService.listAllElements());
		return "cartella/list";
	}

	@GetMapping("/show/{idcartella}")
	public String showCartella(@PathVariable(required = true) Long idcartella, Model model) {
		model.addAttribute("show_cartella_attr", cartellaEsattorialeService.caricaSingoloElementoEager(idcartella));
		return "cartella/show";
	}

	@GetMapping("/edit/{idcartella}")
	public String editCartella(@PathVariable(required = true) Long idcartella, Model model) {
		model.addAttribute("edit_cartella_attr", CartellaEsattorialeDTO
				.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeService.caricaSingoloElemento(idcartella)));
		return "cartella/edit";
	}

	@PostMapping("/update")
	public String updateCartella(
			@Valid @ModelAttribute("edit_cartella_attr") CartellaEsattorialeDTO CartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "cartella/edit";
		}
		cartellaEsattorialeService.aggiorna(CartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaEsattoriale";
	}

	@GetMapping("/delete/{idCartella}")
	public String deleteCartella(@PathVariable(required = true) Long idCartella, Model model,
			RedirectAttributes redirectAttrs) {

		model.addAttribute("delete_cartella_attr", cartellaEsattorialeService.caricaSingoloElemento(idCartella));
		return "cartella/delete";
	}

	@GetMapping("/remove/{idCartella}")
	public String removeCartella(@PathVariable(required = true) Long idCartella, RedirectAttributes redirectAttrs) {

		cartellaEsattorialeService.rimuovi(cartellaEsattorialeService.caricaSingoloElemento(idCartella));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaEsattoriale";
	}

}
