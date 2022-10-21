package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.dao.AbstractMySQLDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	@Override
	public List<Televisore> list() throws Exception {
		
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Televisore> result = new ArrayList<>();
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")){
			
			while(rs.next()) {
				Televisore televisoreTmp = new Televisore();
				televisoreTmp.setId(rs.getLong("id"));
				televisoreTmp.setMarca(rs.getString("marca"));
				televisoreTmp.setModello(rs.getString("modello"));
				televisoreTmp.setPollici(rs.getInt("pollici"));
				televisoreTmp.setDataProduzione(rs.getDate("dataproduzione"));
				result.add(televisoreTmp);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Televisore> voglioTuttiTelevisoriProdottiInIntervalloDate(Date before, Date after) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Televisore voglioIlTelevisorePiuGrande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi() {
		// TODO Auto-generated method stub
		return null;
	}

}
