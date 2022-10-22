package it.prova.televisoredaowithservices.test;

import java.text.SimpleDateFormat;
import java.util.List;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {

		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();

		try {

			testInsert(televisoreService);
			System.out.println();

			testGet(televisoreService);
			System.out.println();

			testUpdate(televisoreService);
			System.out.println();

			testDelete(televisoreService);
			System.out.println();

			testFindByExample(televisoreService);
			System.out.println();

			testVoglioTuttiTelevisoriProdottiInIntervalloDate(televisoreService);
			System.out.println();

			testVoglioIlTelevisorePiuGrande(televisoreService);
			System.out.println();

			testListaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi(televisoreService);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInsert(TelevisoreService televisoreService) throws Exception {
		System.out.println("testInsert INIZIO");
		Televisore televisoreDaInserire = new Televisore("LG", "ABC123", 55,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2020"));
		List<Televisore> elencoPresenti = televisoreService.list();
		int size1 = elencoPresenti.size();
		televisoreService.insert(televisoreDaInserire);
		elencoPresenti = televisoreService.list();
		int size2 = elencoPresenti.size();
		if (size1 + 1 != size2)
			throw new RuntimeException("testInsert FAILED: inserimento non andato a buon fine.");
		System.out.println("testInsert PASSED.");
	}

	private static void testGet(TelevisoreService televisoreService) throws Exception {
		System.out.println("testGet INIZIO");
		List<Televisore> elencoPresenti = televisoreService.list();
		if (elencoPresenti.size() == 0)
			throw new RuntimeException("testGet FAILED: nessun elemento nel db per poter procedere.");
		Televisore result = televisoreService.get(elencoPresenti.get(0).getId());
		if (result.getId() != elencoPresenti.get(0).getId())
			throw new RuntimeException("testGet FAILED: id ottenuto sbagliato.");
		System.out.println("testGet PASSED.");
	}

	private static void testUpdate(TelevisoreService televisoreService) throws Exception {
		System.out.println("testUpdate INIZIO");
		List<Televisore> elencoPresenti = televisoreService.list();
		if (elencoPresenti.size() == 0)
			throw new RuntimeException("testUpdate FAILED: nessun elemento nel db per poter procedere.");
		Televisore televisoreDaAggiornare = new Televisore(elencoPresenti.get(0).getId(), "Samsung", "IOP098", 48,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2018"));
		int result = televisoreService.update(televisoreDaAggiornare);
		if (result == 0)
			throw new RuntimeException("testUpdate FAILED: aggiornamento non andato a buon fine.");
		System.out.println("testUpdate PASSED.");
	}

	private static void testDelete(TelevisoreService televisoreService) throws Exception {
		System.out.println("testDelete INIZIO");
		List<Televisore> elencoPresenti = televisoreService.list();
		if (elencoPresenti.size() == 0)
			throw new RuntimeException("testDelete FAILED: nessun elemento nel db per poter procedere.");
		Televisore televisoreDaEliminare = elencoPresenti.get(0);
		int result = televisoreService.update(televisoreDaEliminare);
		if (result == 0)
			throw new RuntimeException("testDelete FAILED: cancellazione non andata a buon fine.");
		System.out.println("testDelete PASSED.");
	}

	private static void testFindByExample(TelevisoreService televisoreService) throws Exception {
		System.out.println("testFindByExample INIZIO");
		Televisore example = new Televisore("Sa", null, 10, null);
		List<Televisore> result = televisoreService.findByExample(example);
		if (result.isEmpty())
			throw new RuntimeException("testFindByExample FAILED: ricerca non andata a buon fine.");
		System.out.println("testFindByExample PASSED.");
	}

	private static void testVoglioTuttiTelevisoriProdottiInIntervalloDate(TelevisoreService televisoreService)
			throws Exception {
		System.out.println("voglioTuttiTelevisoriProdottiInIntervalloDate INIZIO.");
		List<Televisore> result = televisoreService.voglioTuttiTelevisoriProdottiInIntervalloDate(
				new SimpleDateFormat("dd-MM-yyyy").parse("01-10-1900"),
				new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2090"));
		if (result.isEmpty())
			throw new RuntimeException(
					"voglioTuttiTelevisoriProdottiInIntervalloDate FAILED: ricerca non effettuata correttamente.");
		System.out.println("voglioTuttiTelevisoriProdottiInIntervalloDate PASSED.");
	}

	private static void testVoglioIlTelevisorePiuGrande(TelevisoreService televisoreService) throws Exception {
		System.out.println("testVoglioIlTelevisorePiuGrande INIZIO.");
		List<Televisore> elencoPresenti = televisoreService.list();
		if (elencoPresenti.size() == 0)
			throw new RuntimeException(
					"testVoglioIlTelevisorePiuGrande FAILED: nessun elemento nel db per poter procedere.");
		Televisore result = televisoreService.voglioIlTelevisorePiuGrande();
		if (result == null)
			throw new RuntimeException("testVoglioIlTelevisorePiuGrande FAILED: ricerca non avvenuta con successo.");
		System.out.println("testVoglioIlTelevisorePiuGrande PASSED.");
	}

	private static void testListaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi(TelevisoreService televisoreService)
			throws Exception {
		System.out.println("testListaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi INIZIO.");

		televisoreService
				.insert(new Televisore("LG", "ABC123", 55, new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2022")));
		televisoreService
				.insert(new Televisore("LG", "ABC123", 55, new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2022")));
		televisoreService
				.insert(new Televisore("Sony", "ABC123", 55, new SimpleDateFormat("dd-MM-yyyy").parse("01-07-2022")));

		List<String> result = televisoreService.listaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi();

		if (result.isEmpty())
			throw new RuntimeException(
					"testListaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi FAILED: ricerca non effettuata con successo.");

		/*for (String string : result) {
			System.out.println(string);
		}*/
		System.out.println("testListaDiMarcheDiTelevisoriProdottiNegliUltimi6Mesi PASSED.");
	}

}
