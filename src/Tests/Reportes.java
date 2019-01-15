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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import Pages.remoteScriptExec;

public class Reportes {
	
	remoteScriptExec rSE = new remoteScriptExec();
	String sDateFormat = "dd/MM/yyyy HH:mm:ss";
	String sDateFormatBorn = "dd/MM/yyyy";
	SimpleDateFormat sdfDateFormat;

	//@BeforeClass(alwaysRun=true)
	public void init() throws MalformedURLException, UnknownHostException, FileNotFoundException, IOException, JSchException, SftpException {
		rSE.FTPConnection();
		System.out.println("Connection stablished.");
		rSE.FTPDownload();
		System.out.println("Download completed.");
	}
	
	//@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		
	}
	
	@Test
	public void TS125398_CRM_Interfaz_LCRM_Individuo() throws IOException, ParseException {
		String sName = "_INDIVIDUO_";
		rSE.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rSE.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rSE.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rSE.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertTrue(!sIDIndividuo.isEmpty());
			
			String sNumeroTelefono = lsAux.get(1);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroTelefono, 40));
			
			String sCodUsuarioAlta = lsAux.get(2);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sDepartamentoEmpresa = lsAux.get(3);
			Assert.assertTrue(rSE.verifyTextMaxSize(sDepartamentoEmpresa, 80));
			
			String sMarcaNoLlamar = lsAux.get(4);
			Integer.parseInt(sMarcaNoLlamar);
			Assert.assertTrue(sMarcaNoLlamar.equals("1") || sMarcaNoLlamar.equals("0"));
			
			String sEmail = lsAux.get(5);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rSE.verifyTextMaxSize(sEmail, 80));
			
			String sMarcaEnviarMail = lsAux.get(6);
			Integer.parseInt(sMarcaEnviarMail);
			Assert.assertTrue(sMarcaEnviarMail.equals("1") || sMarcaEnviarMail.equals("0"));
			
			String sMarcaEnviarFax = lsAux.get(7);
			Integer.parseInt(sMarcaEnviarFax);
			Assert.assertTrue(sMarcaEnviarFax.equals("1") || sMarcaEnviarFax.equals("0"));
			
			String sNumeroTelefonoCasa = lsAux.get(8);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroTelefonoCasa, 40));
			
			String sCodUsuarioMod = lsAux.get(9);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaUltInteraccion = lsAux.get(10);
			if (!sFechaUltInteraccion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaUltInteraccion);
			}
			
			String sNumeroMovil = lsAux.get(11);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroMovil, 40));
			
			String sNombre = lsAux.get(12);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNombre, 40));
			
			String sApellido = lsAux.get(13);
			Assert.assertTrue(rSE.verifyTextMaxSize(sApellido, 80));
			
			String sTituloCortesia = lsAux.get(14);
			Assert.assertTrue(rSE.verifyTextMaxSize(sTituloCortesia, 128));
			
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
			Assert.assertTrue(rSE.verifyTextMaxSize(sTipoDocumento, 255));
			
			String sNumeroDocumento = lsAux.get(20);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroDocumento, 30));
			
			String sCUIL = lsAux.get(18);
			if (!sCUIL.isEmpty()) {
				rSE.verificarCUITCUIT(sCUIL, sNumeroDocumento);
			}
			
			String sLicenciaDeConducir = lsAux.get(21);
			Assert.assertTrue(rSE.verifyTextMaxSize(sLicenciaDeConducir, 50));
			
			String sCodEmpleado = lsAux.get(22);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodEmpleado, 255));
			
			String sCodPersonaContacto = lsAux.get(23);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sMarcaFraude = lsAux.get(24);
			Integer.parseInt(sMarcaFraude);
			Assert.assertTrue(sMarcaFraude.equals("1") || sMarcaFraude.equals("0"));
			
			String sGenero = lsAux.get(25);
			Assert.assertTrue(rSE.verifyTextMaxSize(sGenero, 255));
			
			String sEstado = lsAux.get(26);
			Assert.assertTrue(rSE.verifyTextMaxSize(sEstado, 255));
			
			String sFechaNacimiento = lsAux.get(27);
			if (!sFechaNacimiento.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormatBorn);
				sdfDateFormat.parse(sFechaNacimiento);
			}
			
			String sOcupacion = lsAux.get(28);
			Assert.assertTrue(rSE.verifyTextMaxSize(sOcupacion, 50));
			
			String sEstadoLaboral = lsAux.get(29);
			Assert.assertTrue(rSE.verifyTextMaxSize(sEstadoLaboral, 255));
			
			String sNivelSatisfaccion = lsAux.get(30);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNivelSatisfaccion, 255));
			
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
			Assert.assertTrue(rSE.verifyTextMaxSize(sOrigenContacto, 40));
		}
	}
	
	@Test
	public void TS125399_CRM_Interfaz_LCRM_IndividuoCuentaCliente() throws ParseException, IOException {
		String sName = "_INDIVIDUOCUENTACLIENTE_";
		
		rSE.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rSE.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rSE.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rSE.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertTrue(!sIDIndividuo.isEmpty());
			
			String sIDCuentaCliente = lsAux.get(1);
			Assert.assertTrue(rSE.verifyTextMaxSize(sIDCuentaCliente, 255));
			
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
			Assert.assertTrue(rSE.verifyTextMaxSize(sRol, 18));
		}
	}
	
	@Test
	public void TS125400_CRM_Interfaz_LCRM_CuentaCliente() throws ParseException, IOException {
		String sName = "_CUENTACLIENTE_";
		
		rSE.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rSE.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rSE.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdCuentaCliente = lsAux.get(0);
			Assert.assertTrue(rSE.verifyTextMaxSize(sIdCuentaCliente, 18));
			Assert.assertTrue(!sIdCuentaCliente.isEmpty());
			
			String sCodCuenta = lsAux.get(1);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodCuenta, 255));
			Assert.assertTrue(!sCodCuenta.isEmpty());
			
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
			Double.parseDouble(sValorCuenta);
			
			String sIngresoBrutoAnual = lsAux.get(8);
			Double.parseDouble(sIngresoBrutoAnual);
			
			String sIngresoNetoAnual = lsAux.get(9);
			Double.parseDouble(sIngresoNetoAnual);
			
			String sFechaFundacion = lsAux.get(10);
			if (!sFechaFundacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaFundacion);
			}
			
			String sNumeroFax = lsAux.get(11);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroFax, 40));
			
			String sRazonSocial = lsAux.get(12);
			Assert.assertTrue(rSE.verifyTextMaxSize(sRazonSocial, 255));
			Assert.assertTrue(!sRazonSocial.isEmpty());
			
			String sNumeroTelefonoPpal = lsAux.get(13);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroTelefonoPpal, 40));
			
			String sNumeroTelefonoAlternativo = lsAux.get(14);
			Assert.assertTrue(rSE.verifyTextMaxSize(sNumeroTelefonoAlternativo, 40));
			
			String sEstado = lsAux.get(15);
			Assert.assertTrue(rSE.verifyTextMaxSize(sEstado, 255));
			
			String sCUIT = lsAux.get(16);
			if (!sCUIT.isEmpty()) {
				rSE.verificarCUITCUIT(sCUIT, sCUIT.split("-")[1]);
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
			Integer.parseInt(sCantidadEmpleados);
			
			String sIndustria = lsAux.get(20);
			Assert.assertTrue(rSE.verifyTextMaxSize(sIndustria, 40));
			
			String sEmail = lsAux.get(21);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rSE.verifyTextMaxSize(sEmail, 255));
			
			String sSegmentoNivel1 = lsAux.get(22);
			Assert.assertTrue(rSE.verifyTextMaxSize(sSegmentoNivel1, 255));
			
			String sSegmentoNivel2 = lsAux.get(23);
			Assert.assertTrue(rSE.verifyTextMaxSize(sSegmentoNivel2, 255));
			
			String sCodPersonaContacto = lsAux.get(24);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sCodCuentaOrigen = lsAux.get(25);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodCuentaOrigen, 18));
			
			String sFechaCreaAudit = lsAux.get(26);
			Assert.assertTrue(!sIdCuentaCliente.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaCreaAudit);
			
			String sCodUsuariosAlta = lsAux.get(27);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodUsuariosAlta, 18));
			
			String sFechaModAudit = lsAux.get(28);
			Assert.assertTrue(!sFechaModAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaModAudit);
			
			String sCodCuentaPadre = lsAux.get(29);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodCuentaPadre, 18));
			
			String sCodCliente = lsAux.get(30);
			Integer.parseInt(sCodCliente);
			
			String sFechaMod = lsAux.get(31);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodUsuarioMod = lsAux.get(32);
			Assert.assertTrue(rSE.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaCreacion = lsAux.get(33);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sRiesgoCrediticio = lsAux.get(34);
			Assert.assertTrue(rSE.verifyTextMaxSize(sRiesgoCrediticio, 18));
			
			String sTipoCuenta = lsAux.get(35);
			Assert.assertTrue(rSE.verifyTextMaxSize(sTipoCuenta, 80));
		}
	}
	
}