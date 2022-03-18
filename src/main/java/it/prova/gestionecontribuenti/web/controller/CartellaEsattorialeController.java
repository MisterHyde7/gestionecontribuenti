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
	public ModelAndView listAllFilms() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> cartelle = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_list_attribute", CartellaDTO.createCartellaDTOListFromModelList(films, false));
		mv.setViewName("film/list");
		return mv;
	}

	@GetMapping("/insert")
	public String createFilm(Model model) {
		model.addAttribute("insert_film_attr", new CartellaDTO());
		return "film/insert";
	}

	// inietto la request perché ci potrebbe tornare utile per ispezionare i
	// parametri
	@PostMapping("/save")
	public String saveFilm(@Valid @ModelAttribute("insert_film_attr") CartellaDTO CartellaDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per
		// visualizzarne nome e cognome nel campo testo
		if (CartellaDTO.getRegista() == null || CartellaDTO.getRegista().getId() == null)
			result.rejectValue("regista", "regista.notnull");
		else
			CartellaDTO.setRegista(RegistaDTO
					.buildRegistaDTOFromModel(registaService.caricaSingoloElemento(CartellaDTO.getRegista().getId())));

		if (result.hasErrors()) {
			return "film/insert";
		}
		cartellaEsattorialeService.inserisciNuovo(CartellaDTO.buildFilmModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/film";
	}

	@GetMapping("/search")
	public String searchFilm(Model model) {
		model.addAttribute("registi_list_attribute",
				RegistaDTO.createRegistaDTOListFromModelList(registaService.listAllElements()));
		return "film/search";
	}

	@PostMapping("/list")
	public String listFilms(CartellaDTO filmExample, ModelMap model) {
		if (filmExample.getMinutiDurata() == null)
			filmExample.setMinutiDurata(1);
		List<Film> films = cartellaEsattorialeService.findByExample(filmExample.buildFilmModel());
		model.addAttribute("cartella_list_attribute", CartellaDTO.createCartellaDTOListFromModelList(films, false));
		return "film/list";
	}

	@GetMapping("/show/{idFilm}")
	public String showFilm(@PathVariable(required = true) Long idFilm, Model model) {
		model.addAttribute("show_film_attr", cartellaEsattorialeService.caricaSingoloElementoEager(idFilm));
		return "film/show";
	}

	@GetMapping("/edit/{idFilm}")
	public String editFilma(@PathVariable(required = true) Long idFilm, Model model) {
		model.addAttribute("edit_film_attr", CartellaDTO.buildCartellaDTOFromModel(cartellaEsattorialeService.caricaSingoloElemento(idFilm),
				(cartellaEsattorialeService.caricaSingoloElementoEager(idFilm).getRegista().getId() != null)));
		return "film/edit";
	}

	@PostMapping("/update")
	public String updateFilm(@Valid @ModelAttribute("edit_film_attr") CartellaDTO CartellaDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "film/edit";
		}
		cartellaEsattorialeService.aggiorna(CartellaDTO.buildFilmModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/film";
	}

	@GetMapping("/delete/{idFilm}")
	public String prepareDelete(@PathVariable(required = true) Long idFilm, Model model,
			RedirectAttributes redirectAttrs) {

		model.addAttribute("delete_film_attr", cartellaEsattorialeService.caricaSingoloElemento(idFilm));
		return "film/delete";
	}

	@GetMapping("/remove/{idFilm}")
	public String confirm(@PathVariable(required = true) Long idFilm, RedirectAttributes redirectAttrs) {

		cartellaEsattorialeService.rimuovi(cartellaEsattorialeService.caricaSingoloElemento(idFilm));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/film";
	}

}
