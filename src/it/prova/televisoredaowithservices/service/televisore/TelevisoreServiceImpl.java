package it.prova.televisoredaowithservices.service.televisore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.televisoredaowithservices.connection.MyConnection;
import it.prova.televisoredaowithservices.dao.Constants;
import it.prova.televisoredaowithservices.dao.televisore.TelevisoreDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	private TelevisoreDAO televisoreDao;

	public void setTelevisoreDao(TelevisoreDAO televisoreDao) {
		this.televisoreDao = televisoreDao;
	}

	@Override
	public List<Televisore> list() throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);

			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore get(Long idInput) throws Exception {

		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.get(idInput);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.update(input);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);

			result = televisoreDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.delete(input);

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore input) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			televisoreDao.findByExample(input);

			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> voglioTuttiTelevisoriProdottiInIntervalloDate(Date before, Date after) throws Exception {
		List<Televisore> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			televisoreDao.voglioTuttiTelevisoriProdottiInIntervalloDate(before, after);

			result = televisoreDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore voglioIlTelevisorePiuGrande() throws Exception {
		Televisore result = new Televisore();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.voglioIlTelevisorePiuGrande();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<String> listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi() throws Exception {

		List<String> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			televisoreDao.setConnection(connection);
			result = televisoreDao.listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
