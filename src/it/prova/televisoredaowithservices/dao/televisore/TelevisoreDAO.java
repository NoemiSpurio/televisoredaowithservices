package it.prova.televisoredaowithservices.dao.televisore;

import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore> {

	public List<Televisore> voglioTuttiTelevisoriProdottiInIntervalloDate(Date before, Date after) throws Exception;

	public Televisore voglioIlTelevisorePiuGrande() throws Exception;

	public List<String> listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi() throws Exception;

}
