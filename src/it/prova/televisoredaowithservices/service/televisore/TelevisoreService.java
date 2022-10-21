package it.prova.televisoredaowithservices.service.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreService {

	public void setTelevisoreDao(TelevisoreDAO televisoreDao);
	
	public List<Televisore> list() throws Exception;

	public Televisore get(Long idInput) throws Exception;

	public int update(Televisore input) throws Exception;

	public int insert(Televisore input) throws Exception;

	public int delete(Televisore input) throws Exception;

	public List<Televisore> findByExample(Televisore input) throws Exception;
	
	public List<Televisore> voglioTuttiTelevisoriProdottiInIntervalloDate(Date before, Date after) throws Exception;

	public Televisore voglioIlTelevisorePiuGrande() throws Exception;

	public List<String> listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi() throws Exception;

}
