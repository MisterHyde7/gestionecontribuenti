package it.prova.gestionecontribuenti.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {

	@Query("from Contribuente c join fetch c.cartelle where c.id = ?1")
	Contribuente findSingleContribuenteEager(Long id);

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String term,
			String term2);
	
}
