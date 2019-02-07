package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import Pages.remoteScriptExec;

public class Reportes {
	
	remoteScriptExec rsePage = new remoteScriptExec();
	String sDateFormat = "dd/MM/yyyy HH:mm:ss";
	String sDateFormatBorn = "dd/MM/yyyy";
	SimpleDateFormat sdfDateFormat;
	List<String> sListOfFiles = null;
	
	//Before & AfterClass
	
	@BeforeClass(alwaysRun=true)
	public void init() throws MalformedURLException, UnknownHostException, FileNotFoundException, IOException, JSchException, SftpException {
		rsePage.FTPConnection();
		System.out.println("Connection stablished.");
		rsePage.FTPDownload();
		System.out.println("Download completed.");
	}
	
	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		rsePage.deleteAllFiles();
	}
	
	//Tests
	
	//Test #1
	@Test
	public void TS125398_CRM_Interfaz_LCRM_Individuo() throws IOException, ParseException {
		String sName = "_INDIVIDUO_";
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertFalse(sIDIndividuo.isEmpty());
			
			String sNumeroTelefono = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefono, 40));
			
			String sCodUsuarioAlta = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sDepartamentoEmpresa = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sDepartamentoEmpresa, 80));
			
			String sMarcaNoLlamar = lsAux.get(4);
			Integer.parseInt(sMarcaNoLlamar);
			Assert.assertTrue(sMarcaNoLlamar.equals("1") || sMarcaNoLlamar.equals("0"));
			
			String sEmail = lsAux.get(5);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEmail, 80));
			
			String sMarcaEnviarMail = lsAux.get(6);
			Integer.parseInt(sMarcaEnviarMail);
			Assert.assertTrue(sMarcaEnviarMail.equals("1") || sMarcaEnviarMail.equals("0"));
			
			String sMarcaEnviarFax = lsAux.get(7);
			Integer.parseInt(sMarcaEnviarFax);
			Assert.assertTrue(sMarcaEnviarFax.equals("1") || sMarcaEnviarFax.equals("0"));
			
			String sNumeroTelefonoCasa = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoCasa, 40));
			
			String sCodUsuarioMod = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaUltInteraccion = lsAux.get(10);
			if (!sFechaUltInteraccion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaUltInteraccion);
			}
			
			String sNumeroMovil = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroMovil, 40));
			
			String sNombre = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombre, 40));
			
			String sApellido = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sApellido, 80));
			
			String sTituloCortesia = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTituloCortesia, 128));
			
			String sFechaAltaContacto = lsAux.get(15);
			if (!sFechaAltaContacto.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaAltaContacto);
			}
			
			String sEdad = lsAux.get(16);
			Integer.parseInt(sEdad);
			
			String sIdContacto = lsAux.get(17);
			if (!sIdContacto.isEmpty()) {
				Integer.parseInt(sIdContacto);
			}
			
			String sTipoDocumento = lsAux.get(19);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoDocumento, 255));
			
			String sNumeroDocumento = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroDocumento, 30));
			
			String sCUIL = lsAux.get(18);
			if (!sCUIL.isEmpty()) {
				rsePage.verifyCUITCUIT(sCUIL, sNumeroDocumento);
			}
			
			String sLicenciaDeConducir = lsAux.get(21);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sLicenciaDeConducir, 50));
			
			String sCodEmpleado = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodEmpleado, 255));
			
			String sCodPersonaContacto = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sMarcaFraude = lsAux.get(24);
			Integer.parseInt(sMarcaFraude);
			Assert.assertTrue(sMarcaFraude.equals("1") || sMarcaFraude.equals("0"));
			
			String sGenero = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sGenero, 255));
			
			String sEstado = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sFechaNacimiento = lsAux.get(27);
			if (!sFechaNacimiento.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormatBorn);
				sdfDateFormat.parse(sFechaNacimiento);
			}
			
			String sOcupacion = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sOcupacion, 50));
			
			String sEstadoLaboral = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoLaboral, 255));
			
			String sNivelSatisfaccion = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNivelSatisfaccion, 255));
			
			String sFechaCreaAudit = lsAux.get(31);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(32);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaMod = lsAux.get(33);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sFechaCreacion = lsAux.get(34);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sOrigenContacto = lsAux.get(35);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sOrigenContacto, 40));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #2
	@Test
	public void TS125399_CRM_Interfaz_LCRM_IndividuoCuentaCliente() throws ParseException, IOException {
		String sName = "_INDIVIDUOCUENTACLIENTE_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertFalse(sIDIndividuo.isEmpty());
			
			String sIDCuentaCliente = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCuentaCliente, 255));
			
			String sFechaCreaAudit = lsAux.get(2);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(3);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sRol = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRol, 18));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #3
	@Test
	public void TS125400_CRM_Interfaz_LCRM_CuentaCliente() throws ParseException, IOException {
		String sName = "_CUENTACLIENTE_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdCuentaCliente = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaCliente, 18));
			Assert.assertFalse(sIdCuentaCliente.isEmpty());
			
			String sCodCuenta = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuenta, 255));
			Assert.assertFalse(sCodCuenta.isEmpty());
			
			String sMarcaFraude = lsAux.get(2);
			Integer.parseInt(sMarcaFraude);
			Assert.assertTrue(sMarcaFraude.equals("1") || sMarcaFraude.equals("0"));
			
			String sMarcaPartner = lsAux.get(3);
			Integer.parseInt(sMarcaPartner);
			Assert.assertTrue(sMarcaPartner.equals("1") || sMarcaPartner.equals("0"));
			
			String sMarcaPrensa = lsAux.get(4);
			Integer.parseInt(sMarcaPrensa);
			Assert.assertTrue(sMarcaPrensa.equals("1") || sMarcaPrensa.equals("0"));
			
			String sMarcaNoNominado = lsAux.get(5);
			Integer.parseInt(sMarcaNoNominado);
			Assert.assertTrue(sMarcaNoNominado.equals("1") || sMarcaNoNominado.equals("0"));
			
			String sMarcaVIP = lsAux.get(6);
			Integer.parseInt(sMarcaVIP);
			Assert.assertTrue(sMarcaVIP.equals("1") || sMarcaVIP.equals("0"));
			
			String sValorCuenta = lsAux.get(7);
			if (!sValorCuenta.isEmpty()) {
				Double.parseDouble(sValorCuenta);
			}
			
			String sIngresoBrutoAnual = lsAux.get(8);
			if (!sIngresoBrutoAnual.isEmpty()) {
				Double.parseDouble(sIngresoBrutoAnual);
			}
			
			String sIngresoNetoAnual = lsAux.get(9);
			if (!sIngresoNetoAnual.isEmpty()) {
				Double.parseDouble(sIngresoNetoAnual);
			}
			
			String sFechaFundacion = lsAux.get(10);
			if (!sFechaFundacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaFundacion);
			}
			
			String sNumeroFax = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroFax, 40));
			
			String sRazonSocial = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRazonSocial, 255));
			Assert.assertFalse(sRazonSocial.isEmpty());
			
			String sNumeroTelefonoPpal = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoPpal, 40));
			
			String sNumeroTelefonoAlternativo = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoAlternativo, 40));
			
			String sEstado = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sCUIT = lsAux.get(16);
			if (!sCUIT.isEmpty()) {
				rsePage.verifyCUITCUIT(sCUIT, sCUIT.split("-")[1]);
			}
			
			String sMarcaJubilado = lsAux.get(17);
			Integer.parseInt(sMarcaJubilado);
			Assert.assertTrue(sMarcaJubilado.equals("1") || sMarcaJubilado.equals("0"));
			
			String sFechaModEmail = lsAux.get(18);
			if (!sFechaModEmail.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModEmail);
			}
			
			String sCantidadEmpleados = lsAux.get(19);
			if (!sCantidadEmpleados.isEmpty()) {
				Integer.parseInt(sCantidadEmpleados);
			}
			
			String sIndustria = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIndustria, 40));
			
			String sEmail = lsAux.get(21);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEmail, 255));
			
			String sSegmentoNivel1 = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSegmentoNivel1, 255));
			
			String sSegmentoNivel2 = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSegmentoNivel2, 255));
			
			String sCodPersonaContacto = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sCodCuentaOrigen = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaOrigen, 18));
			
			String sFechaCreaAudit = lsAux.get(26);
			Assert.assertFalse(sIdCuentaCliente.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaCreaAudit);
			
			String sCodUsuariosAlta = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuariosAlta, 18));
			
			String sFechaModAudit = lsAux.get(28);
			Assert.assertFalse(sFechaModAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaModAudit);
			
			String sCodCuentaPadre = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaPadre, 18));
			
			String sCodCliente = lsAux.get(30);
			Integer.parseInt(sCodCliente);
			
			String sFechaMod = lsAux.get(31);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodUsuarioMod = lsAux.get(32);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaCreacion = lsAux.get(33);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sRiesgoCrediticio = lsAux.get(34);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRiesgoCrediticio, 18));
			
			String sTipoCuenta = lsAux.get(35);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoCuenta, 80));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #4
	@Test
	public void TS125401_CRM_Interfaz_LCRM_IndividuoCuentaFacturacion() throws ParseException, IOException {
		String sName = "_INDIVIDUOCUENTAFACTURACION_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdIndividuo, 18));
			Assert.assertFalse(sIdIndividuo.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 255));
			
			String sFechaCreaAudit = lsAux.get(2);
			Assert.assertFalse(sFechaCreaAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaCreaAudit);
			
			String sFechaModAudit = lsAux.get(3);
			Assert.assertFalse(sFechaModAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaModAudit);
			
			String sRol = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRol, 18));
			Assert.assertTrue(sRol.equalsIgnoreCase("Referente de Pago")); //Ask. It says 'Campo Fijo'
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #5
	@Test
	public void TS125402_CRM_Interfaz_LCRM_CuentaFacturacion() throws ParseException, IOException {
		String sName = "_CUENTAFACTURACION_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sCodCuenta = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuenta, 255));
			Assert.assertFalse(sCodCuenta.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 18));
			Assert.assertFalse(sIdCuentaFacturacion.isEmpty());
			
			String sMarcaDeudaFinanciada = lsAux.get(2);
			Assert.assertFalse(sMarcaDeudaFinanciada.isEmpty());
			Integer.parseInt(sMarcaDeudaFinanciada);
			Assert.assertTrue(sMarcaDeudaFinanciada.equals("1") || sMarcaDeudaFinanciada.equals("0"));
			
			String sMarcaFOL = lsAux.get(3);
			Assert.assertFalse(sMarcaFOL.isEmpty());
			Integer.parseInt(sMarcaFOL);
			Assert.assertTrue(sMarcaFOL.equals("1") || sMarcaFOL.equals("0"));
			
			String sMarcaCompraFinanciada = lsAux.get(4);
			Assert.assertFalse(sMarcaCompraFinanciada.isEmpty());
			Integer.parseInt(sMarcaCompraFinanciada);
			Assert.assertTrue(sMarcaCompraFinanciada.equals("1") || sMarcaCompraFinanciada.equals("0"));
			
			String sMarcaDebito = lsAux.get(5);
			Assert.assertFalse(sMarcaDebito.isEmpty());
			Integer.parseInt(sMarcaDebito);
			Assert.assertTrue(sMarcaDebito.equals("1") || sMarcaDebito.equals("0"));
			
			String sDireccionEmail = lsAux.get(6);
			Assert.assertFalse(sDireccionEmail.isEmpty());
			Assert.assertTrue(sDireccionEmail.contains("@"));
			Assert.assertTrue(rsePage.verifyTextMaxSize(sDireccionEmail, 80));
			
			String sCodCuentaFacturacion = lsAux.get(7);
			Assert.assertFalse(sCodCuentaFacturacion.isEmpty());
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaFacturacion, 5));
			Integer.parseInt(sCodCuentaFacturacion);
			
			String sCodCuentaPadre = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaPadre, 18));
			
			String sCodCuentaOrigen = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaOrigen, 18));
			
			String sIdMedioDePago = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdMedioDePago, 18));
			
			String sCodUsuarioAlta = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sCodUsuarioMod = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sCodCliente = lsAux.get(13);
			Integer.parseInt(sCodCliente);
			
			String sNumeroTelefonoPpal = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoPpal, 40));
			
			String sNumeroTelefonoAlternativo = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoAlternativo, 40));
			
			String sCicloFacturacion = lsAux.get(16);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCicloFacturacion, 255));
			
			String sIDDireccionFacturacion = lsAux.get(17);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDDireccionFacturacion, 255));
			
			String sIDDireccionEnvio = lsAux.get(18);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDDireccionEnvio, 255));
			
			String sMarcaSocio = lsAux.get(19);
			Integer.parseInt(sMarcaSocio);
			Assert.assertTrue(sMarcaSocio.equals("1") || sMarcaSocio.equals("0"));
			
			String sMarcaMorosidad = lsAux.get(20);
			Integer.parseInt(sMarcaMorosidad);
			Assert.assertTrue(sMarcaMorosidad.equals("1") || sMarcaMorosidad.equals("0"));
			
			String sFechaModEmail = lsAux.get(21);
			if (!sFechaModEmail.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModEmail);
			}
			
			String sPuntosAcumulados = lsAux.get(22);
			Integer.parseInt(sPuntosAcumulados);
			
			String sCategoriaSocio = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCategoriaSocio, 255));
			
			String sTipoCuenta = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoCuenta, 40));
			
			String sMetodoEntrega = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sMetodoEntrega, 255));
			
			String sFrecuenciaFacturacion = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sFrecuenciaFacturacion, 255));
			
			String sSLA = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSLA, 255));
			
			String sTipoRegistro = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoRegistro, 18));
			
			String sTipoDePago = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoDePago, 255));
			
			String sRiesgoCrediticio = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRiesgoCrediticio, 18));
			
			String sContactoPrimario = lsAux.get(31);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sContactoPrimario, 255));
			
			String sFechaCreaAudit = lsAux.get(32);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(33);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaCreacion = lsAux.get(34);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaMod = lsAux.get(35);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sEstado = lsAux.get(36);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sPreferenciasContacto = lsAux.get(37);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sPreferenciasContacto, 255));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #6
	@Test
	public void TS125403_CRM_Interfaz_LCRM_ProductoAquirido() throws ParseException, IOException {
		String sName = "_PRODUCTOADQUIRIDO_";
		
		//rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDProductoAdquirido = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProductoAdquirido, 18));
			Assert.assertFalse(sIDProductoAdquirido.isEmpty());
			
			String sIDCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCuentaFacturacion, 18));
			Assert.assertFalse(sIDCuentaFacturacion.isEmpty());
			
			String sIDProducto = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProducto, 18));
			Assert.assertFalse(sIDProducto.isEmpty());
			
			String sMarcaProdCompetencia = lsAux.get(3);
			Integer.parseInt(sMarcaProdCompetencia);
			
			String sFechaInstalacion = lsAux.get(4);
			if (!sFechaInstalacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaInstalacion);
			}
			
			String sCodUsuarioAlta = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sCodUsuarioMod = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sCantUnidadesProdAdquiridas = lsAux.get(7);
			Double.parseDouble(sCantUnidadesProdAdquiridas);
			
			String sTipoProducto = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoProducto, 255));
			
			String sEstadoProductoAdq = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProductoAdq, 40));
			
			String sNombreProducto = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombreProducto, 255));
			
			String sNumeroSerie = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroSerie, 80));
			
			String sEstadoEnRed = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoEnRed, 255));
			
			String sSubestadoProductoAdq = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSubestadoProductoAdq, 255));
			
			String sNumeroLinea = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroLinea, 10));
			
			String sFechaActivacion = lsAux.get(15);
			if (!sFechaActivacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaActivacion);
			}
			
			String sEstadoProvisionamiento = lsAux.get(16);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProvisionamiento, 255));
			
			String sFamiliaProducto = lsAux.get(17);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sFamiliaProducto, 255));
			
			String sCodProductoPadre = lsAux.get(18);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoPadre, 255));
			
			String sFechaCreacion = lsAux.get(19);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaCreaAudit = lsAux.get(20);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(21);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sIMEI = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIMEI, 15));
			
			String sIdOrdenItem = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdOrdenItem, 18));
			Assert.assertFalse(sIdOrdenItem.isEmpty());
			
			String sCodProductoRaiz = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoRaiz, 255));
			
			String sICCID = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sICCID, 255));
			
			String sNMU = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNMU, 200));
			
			String sGamaEquipo = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sGamaEquipo, 200));
			
			String sCodProducto = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProducto, 510));
			
			String sIMSI = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIMSI, 512));
			
			String sCodSuscripcion = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodSuscripcion, 72));
			
			String sIDProductoAdquiridoReferente = lsAux.get(31);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProductoAdquiridoReferente, 510));
			
			String sMarcaMigracion = lsAux.get(32);
			if (!sMarcaMigracion.isEmpty()) {
				Integer.parseInt(sMarcaMigracion);
				Assert.assertTrue(sMarcaMigracion.equals("1") || sMarcaMigracion.equals("0"));
			}
			
			String sMarcaGarantiaInvalida = lsAux.get(33);
			if (!sMarcaGarantiaInvalida.isEmpty()) {
				Integer.parseInt(sMarcaGarantiaInvalida);
				Assert.assertTrue(sMarcaGarantiaInvalida.equals("1") || sMarcaGarantiaInvalida.equals("0"));
			}
			
			String sNivelJerarquia = lsAux.get(34);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNivelJerarquia, 255));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #7
	@Test
	public void TS125404_CRM_Interfaz_LCRM_Orden() throws ParseException, IOException {
		String sName = "_ORDEN_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDOrden = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDOrden, 18));
			Assert.assertFalse(sIDOrden.isEmpty());
			
			String sNumeroOrden = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroOrden, 30));
			Assert.assertFalse(sNumeroOrden.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 18));
			Assert.assertFalse(sIdCuentaFacturacion.isEmpty());
			
			String sTipoGestion = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoGestion, 255));
			
			String sCodUsuarioAlta = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sTipoCanalOrigen = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoCanalOrigen, 510));
			
			String sEstadoOrden = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoOrden, 40));
			
			String sMetodoEntrega = lsAux.get(7);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sMetodoEntrega, 255));
			
			String sPrecioTotalOrden = lsAux.get(8);
			Double.parseDouble(sPrecioTotalOrden);
			
			String sFechaInicioOrden = lsAux.get(9);
			if (!sFechaInicioOrden.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaInicioOrden);
			}
			
			String sFechaFinOrden = lsAux.get(10);
			if (!sFechaFinOrden.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaFinOrden);
			}
			
			String sFechaVenta = lsAux.get(11);
			if (!sFechaVenta.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaVenta);
			}
			
			String sFechaEntrega = lsAux.get(12);
			if (!sFechaEntrega.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaEntrega);
			}
			
			String sFechaModificacion = lsAux.get(13);
			if (!sFechaModificacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModificacion);
			}
			
			String sCodUsuarioMod = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sEstadoTrackeo = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoTrackeo, 255));
			
			String sFechaCreaAudit = lsAux.get(16);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(17);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaCreacion = lsAux.get(18);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaMod = lsAux.get(19);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sNumeroComprobante = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroComprobante, 255));
			
			String sNumeroPreFactura = lsAux.get(21);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroPreFactura, 255));
			
			String sCodMetodoPago = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodMetodoPago, 255));
			
			String sCodBanco = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodBanco, 10));
			
			String sCodTarjeta = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodTarjeta, 10));
			
			String sCFT = lsAux.get(25);
			if (!sCFT.isEmpty()) {
				Double.parseDouble(sCFT);
			}
			
			String sCanalOrigen = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCanalOrigen, 255));
			
			String sEstadoProvisionamientoOrden = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProvisionamientoOrden, 255));
			
			String sFechaActivacion = lsAux.get(28);
			if (!sFechaActivacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaActivacion);
			}
			
			String sIDPuntoDeVenta = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDPuntoDeVenta, 18));
			
			String sTipoSubgestion = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoSubgestion, 255));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #7
	@Test
	public void TS125405_CRM_Interfaz_LCRM_OrdenItem() throws ParseException, IOException {
		String sName = "_ORDENITEM_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdOrdenItem = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdOrdenItem, 18));
			Assert.assertFalse(sIdOrdenItem.isEmpty());
			
			String sNumeroOrdenItem = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroOrdenItem, 30));
			Assert.assertFalse(sNumeroOrdenItem.isEmpty());
			
			String IdOrden = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(IdOrden, 30));
			Assert.assertFalse(IdOrden.isEmpty());
			
			String sPrecioEfectivoUnicaVez = lsAux.get(3);
			if (!sPrecioEfectivoUnicaVez.isEmpty()) {
				Double.parseDouble(sPrecioEfectivoUnicaVez);
			}
			
			String sPrecioRecurrente = lsAux.get(4);
			if (!sPrecioRecurrente.isEmpty()) {
				Double.parseDouble(sPrecioRecurrente);				
			}
			
			String sMRC = lsAux.get(5);
			if (!sMRC.isEmpty()) {
				Double.parseDouble(sMRC);
			}
			
			String sPrecioDctoUnicaVez = lsAux.get(6);
			if (!sPrecioDctoUnicaVez.isEmpty()) {
				Double.parseDouble(sPrecioDctoUnicaVez);
			}
			
			String sCargoUnicaVez = lsAux.get(7);
			if (!sCargoUnicaVez.isEmpty()) {
				Double.parseDouble(sCargoUnicaVez);
			}
			
			String sPrecioTotalUnicaVez = lsAux.get(8);
			if (!sPrecioTotalUnicaVez.isEmpty()) {
				Double.parseDouble(sPrecioTotalUnicaVez);
			}
			
			String sPrecioCalculadoRecurrente = lsAux.get(9);
			if (!sPrecioCalculadoRecurrente.isEmpty()) {
				Double.parseDouble(sPrecioCalculadoRecurrente);
			}
			
			String sCargoRecurrente = lsAux.get(10);
			if (!sCargoRecurrente.isEmpty()) {
				Double.parseDouble(sCargoRecurrente);
			}
			
			String sTotalRecurrente = lsAux.get(11);
			if (!sTotalRecurrente.isEmpty()) {
				Double.parseDouble(sTotalRecurrente);
			}
			
			String sNumeroSecuencia = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroSecuencia, 255));
			
			String sCantidadItem = lsAux.get(13);
			if (!sCantidadItem.isEmpty()) {
				Double.parseDouble(sCantidadItem);
			}
			
			String sPrecioUnitario = lsAux.get(14);
			if (!sPrecioUnitario.isEmpty()) {
				Double.parseDouble(sPrecioUnitario);
			}
			
			String sAccion = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sAccion, 255));
			
			String sFechaCreaAudit = lsAux.get(16);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(17);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sEstadoProvisionamientoItem = lsAux.get(18);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProvisionamientoItem, 255));
			
			String sSubaccion = lsAux.get(19);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSubaccion, 255));
			
			String sEstadoOrdenItem = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoOrdenItem, 255));
			
			String sPrecioLista = lsAux.get(21);
			if (!sPrecioLista.isEmpty()) {
				Double.parseDouble(sPrecioLista);
			}
			
			String sIdProducto = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdProducto, 18));
			
			String sMarcaFacturable = lsAux.get(23);
			if (!sMarcaFacturable.isEmpty()) {
				Integer.parseInt(sMarcaFacturable);
				Assert.assertTrue(sMarcaFacturable.equals("1") || sMarcaFacturable.equals("0"));
			}
			
			String sCodDeposito = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodDeposito, 10));
			
			String sCodOperacion = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodOperacion, 10));
			
			String sFechaCreacion = lsAux.get(26);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sCodUsuarioAlta = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sCodUsuarioMod = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaMod = lsAux.get(29);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodProductoRaiz = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoRaiz, 255));
			
			String sCodProductoPadre = lsAux.get(31);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoPadre, 255));
			
			String sIdProductoAdquiridoReferente = lsAux.get(32);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdProductoAdquiridoReferente, 255));
			
			String sTipoAjuste = lsAux.get(33);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoAjuste, 255));
			
			String sMonto = lsAux.get(34);
			if (!sMonto.isEmpty()) {
				Double.parseDouble(sMonto);
			}
			
			String sValorAjuste = lsAux.get(35);
			if (!sMonto.isEmpty()) {
				Double.parseDouble(sValorAjuste);
			}
			
			String sValorBase = lsAux.get(36);
			if (!sValorBase.isEmpty()) {
				Double.parseDouble(sValorBase);
			}
			
			String sEstadoStock = lsAux.get(37);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoStock, 20));
			
			String sMarcaNoProvisionable = lsAux.get(38);
			Integer.parseInt(sMarcaNoProvisionable);
			Assert.assertTrue(sMarcaNoProvisionable.equals("1") || sMarcaNoProvisionable.equals("0"));
		}
	}
	
	//Test #8
	//@Test
	public void TS125406_CRM_Interfaz_LCRM_CustomerANI() throws ParseException, IOException {
		String sName = "_CUSTOMERANY_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #9
	//@Test
	public void TS125407_CRM_Interfaz_LCRM_Lineas_pre_activadas() throws ParseException, IOException {
		String sName = "_LINEASPREACTIVADAS_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #10
	//@Test
	public void TS125408_CRM_Interfaz_LCRM_CuentaFacturacionInsercion() throws ParseException, IOException {
		String sName = "__";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #11
	@Test
	public void TS125409_CRM_Interfaz_LCRM_Acuerdoservicio() throws ParseException, IOException {
		String sName = "_ACUERDOSERVICIO_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdAcuerdoServicio = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdAcuerdoServicio, 18));
			
			String sAcuerdo = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sAcuerdo, 255));
			
			String sFechaCreacion = lsAux.get(2);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sCodUsuarioAlta = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sFechaMod = lsAux.get(4);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodUsuarioMod = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sIdCuentaFacturacion = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 18));
			
			String sTipoAcuerdo = lsAux.get(7);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoAcuerdo, 40));
			
			String sIdProductoAdquirido = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdProductoAdquirido, 40));
			
			String sFechaDesdeAcuerdo = lsAux.get(9);
			if (!sFechaDesdeAcuerdo.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaDesdeAcuerdo);
			}
			
			String sFechaHastaAcuerdo = lsAux.get(10);
			if (!sFechaHastaAcuerdo.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaHastaAcuerdo);
			}
			
			String sMarcaIncidente = lsAux.get(11);
			if (!sMarcaIncidente.isEmpty()) {
				Integer.parseInt(sMarcaIncidente);
				Assert.assertTrue(sMarcaIncidente.equals("1") || sMarcaIncidente.equals("0"));
			}
			
			String sEstadoAcuerdo = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoAcuerdo, 255));
			
			String sFechaCreaAudit = lsAux.get(13);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(14);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
		}
	}
	
	//Test #12
	//@Test
	public void TS125410_CRM_Interfaz_LCRM_Contactos() throws ParseException, IOException {
		String sName = "_CONTACTOS_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #13
	//@Test
	public void TS125412_CRM_Interfaz_LCRM_ProductoAdquiridoLinea() throws ParseException, IOException {
		String sName = "_PRODUCTOADQUIRIDOLINEA_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #14
	@Test
	public void TS125413_CRM_Interfaz_LCRM_ProductoAdquiridoEquipos() throws ParseException, IOException {
		String sName = "_PRODUCTOADQUIRIDOEQUIPOS_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			
		}
	}
	
	//Test #15
	@Test
	public void TS125414_CRM_Interfaz_LCRM_Ajuste() throws ParseException, IOException {
		String sName = "_AJUSTE_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDAjuste = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDAjuste, 18));
			
			String sNombreCasoAjuste = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombreCasoAjuste, 80));
			
			String sFechaCreacion = lsAux.get(2);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sCodUsuarioCrea = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioCrea, 18));
			
			String sFechaMod = lsAux.get(4);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodUsuarioMod = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sIDCuentaFacturacion = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCuentaFacturacion, 18));
			
			String sMonto = lsAux.get(7);
			Double.parseDouble(sMonto);
			
			String sIDMetodoDePago = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDMetodoDePago, 18));
			
			String sEstado = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sTipoAjuste = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoAjuste, 255));
			
			String sIDOrden = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDOrden, 18));
			
			String sFechaCreaAudit = lsAux.get(12);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(13);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sIDCaso = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCaso, 18));
		}
	}
	
	//Test #16
	@Test
	public void TS125415_CRM_Interfaz_LCRM_ItemProducto2() throws ParseException, IOException {
		String sName = "_ITEMPRODUCTO2_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDRaiz = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDRaiz, 18));
			
			String sNivel = lsAux.get(1);
			Integer.parseInt(sNivel);
			
			String sIDProducto = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProducto, 18));
			
			String sIDPadre =  lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDPadre, 18));
			
			String sNombreProducto = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombreProducto, 255));
		}
	}
	
	//Test #17
	@Test
	public void TS125416_CRM_Interfaz_LCRM_Producto() throws ParseException, IOException {
		String sName = "_PRODUCTO_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdProducto = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdProducto, 18));
			Assert.assertFalse(sIdProducto.isEmpty());
			
			String sCodUsuarioAlta = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sFechaCreacion = lsAux.get(2);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sDescripcion = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sDescripcion, 255));
			
			String sFamiliaProducto = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sFamiliaProducto, 40));
			
			String sMarcaBorrar = lsAux.get(5);
			Integer.parseInt(sMarcaBorrar);
			Assert.assertTrue(sMarcaBorrar.equals("1") || sMarcaBorrar.equals("0"));
			
			String sCodUsuarioMod = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaMod = lsAux.get(7);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sNombreProducto = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombreProducto, 255));
			
			String sCodProducto = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProducto, 255));
			
			String sTipoSim = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoSim, 255));
			
			String sMarcaNoAssetisable = lsAux.get(11);
			Integer.parseInt(sMarcaNoAssetisable);
			Assert.assertTrue(sMarcaNoAssetisable.equals("1") || sMarcaNoAssetisable.equals("0"));
			
			String sMarcaOrderable = lsAux.get(12);
			Integer.parseInt(sMarcaOrderable);
			Assert.assertTrue(sMarcaOrderable.equals("1") || sMarcaOrderable.equals("0"));
			
			String sTipoEspecificacion = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoEspecificacion, 255));
			
			String sEstadoProducto = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProducto, 255));
			
			String sSubtipoProducto = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSubtipoProducto, 255));
			
			String sTipoProducto = lsAux.get(16);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoProducto, 255));
			
			String sFechaCreaAudit = lsAux.get(17);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(18);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sSistemaOrigen = lsAux.get(19);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSistemaOrigen, 255));
		}
	}
	
}