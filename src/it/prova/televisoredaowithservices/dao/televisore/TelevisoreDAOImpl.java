package it.prova.televisoredaowithservices.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
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

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (idInput == null)
			throw new Exception("Id non valido.");
		Televisore result = new Televisore();

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id = ?;")) {

			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result.setId(rs.getLong("id"));
					result.setMarca(rs.getString("marca"));
					result.setModello(rs.getString("modello"));
					result.setPollici(rs.getInt("pollici"));
					result.setDataProduzione(rs.getDate("dataproduzione"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Override
	public int update(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Input non valido.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"update televisore set marca = ?, modello = ?, pollici = ?, dataproduzione = ? where id = ?;")) {

			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));
			ps.setLong(5, input.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Override
	public int insert(Televisore input) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"insert into televisore (marca, modello, pollici, dataproduzione) values (?,?,?,?);")) {

			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, new java.sql.Date(input.getDataProduzione().getTime()));

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Input non valido.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("delete from televisore where id = ?;")) {

			ps.setLong(1, input.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Televisore> findByExample(Televisore example) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (example == null)
			throw new Exception("Input non valido.");
		List<Televisore> result = new ArrayList<>();

		String query = "select * from televisore where ";

		if (!(example.getMarca() == null || example.getMarca().isBlank()))
			query += "marca like ? and ";

		if (!(example.getModello() == null || example.getModello().isBlank()))
			query += "modello like ? and ";

		if (example.getPollici() > 0)
			query += "pollici > ? and ";

		if (example.getDataProduzione() != null)
			query += "dataproduzione > ? and ";

		query += "true;";

		try (PreparedStatement ps = connection.prepareStatement(query)) {

			int counter = 1;
			if (!(example.getMarca() == null || example.getMarca().isBlank())) {
				String marca = example.getMarca() + "%";
				ps.setString(counter, marca);
				counter++;
			}

			if (!(example.getModello() == null || example.getModello().isBlank())) {
				String modello = example.getModello() + "%";
				ps.setString(counter, modello);
				counter++;
			}

			if (example.getPollici() > 0) {
				ps.setInt(counter, example.getPollici());
				counter++;
			}

			if (example.getDataProduzione() != null) {
				ps.setDate(counter, new java.sql.Date(example.getDataProduzione().getTime()));
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Televisore televisoreTmp = new Televisore();
					televisoreTmp.setId(rs.getLong("id"));
					televisoreTmp.setMarca(rs.getString("marca"));
					televisoreTmp.setModello(rs.getString("modello"));
					televisoreTmp.setPollici(rs.getInt("pollici"));
					televisoreTmp.setDataProduzione(rs.getDate("dataproduzione"));
					result.add(televisoreTmp);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Televisore> voglioTuttiTelevisoriProdottiInIntervalloDate(Date before, Date after) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		if (before == null || after == null)
			throw new Exception("Input non valido.");
		List<Televisore> result = new ArrayList<>();
		try (PreparedStatement ps = connection
				.prepareStatement("select * from televisore where dataproduzione between ? and ?;")) {

			ps.setDate(1, new java.sql.Date(before.getTime()));
			ps.setDate(2, new java.sql.Date(after.getTime()));

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Televisore televisoreTmp = new Televisore();
					televisoreTmp.setId(rs.getLong("id"));
					televisoreTmp.setMarca(rs.getString("marca"));
					televisoreTmp.setModello(rs.getString("modello"));
					televisoreTmp.setPollici(rs.getInt("pollici"));
					televisoreTmp.setDataProduzione(rs.getDate("dataproduzione"));
					result.add(televisoreTmp);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore voglioIlTelevisorePiuGrande() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		Televisore result = new Televisore();
		try (Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select * from televisore order by pollici desc, id limit 1;")) {

			if (rs.next()) {
				result.setId(rs.getLong("id"));
				result.setMarca(rs.getString("marca"));
				result.setModello(rs.getString("modello"));
				result.setPollici(rs.getInt("pollici"));
				result.setDataProduzione(rs.getDate("dataproduzione"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<String> listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<String> result = new ArrayList<>();

		LocalDate date = LocalDate.now().minusMonths(6);

		try (PreparedStatement ps = connection
				.prepareStatement("select distinct marca from televisore where dataproduzione > ?;")) {

			ps.setDate(1, java.sql.Date.valueOf(date));

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					result.add(rs.getString("marca"));
				}
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
