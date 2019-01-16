package Pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.testng.Assert;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class remoteScriptExec {
	
	Session sSession;
	
	public void FTPConnection() {
		JSch jsch = new JSch();
		
		try {
			//Open a Session to remote SSH server and Connect.
			//Set User and IP of the remote host and SSH port.
			sSession = jsch.getSession("z001445", "10.76.60.21", 22);
			//When we do SSH to a remote host for the 1st time or if key at the remote host 
			//changes, we will be prompted to confirm the authenticity of remote host. 
			//This check feature is controlled by StrictHostKeyChecking ssh parameter. 
			//By default StrictHostKeyChecking  is set to yes as a security measure.
			sSession.setConfig("StrictHostKeyChecking", "no");
			//Set password
			sSession.setPassword("Pr0pojin_2");
			sSession.connect(30000);
			
			//Create the execution channel over the session
			ChannelExec ceChannelExec = (ChannelExec) sSession.openChannel("exec");
			// Set the command to execute on the channel and execute the command
			ceChannelExec.setCommand("/infra_shared/ScriptsExe/S_REPL00/IC_REPLICA/referencia2valor.sh");
			ceChannelExec.setCommand("cd /infra_shared/ScriptsExe/S_INTE00/IB_CRM/");
			ceChannelExec.connect();
			
			// Get an InputStream from this channel and read messages, generated 
			// by the executing command, from the remote side.
			InputStream isInputStream = ceChannelExec.getInputStream();
			BufferedReader brReader = new BufferedReader(new InputStreamReader(isInputStream));
			String sLine;
			while ((sLine = brReader.readLine()) != null) {
				System.out.println(sLine);
			}
			
			//Command execution completed here.
			
			//Retrieve the exit status of the executed command
			int iExitStatus = ceChannelExec.getExitStatus();
			if (iExitStatus > 0) {
				System.out.println("Remote script exec error! " + iExitStatus);
			}
			//Disconnect the Session
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sSession.disconnect();
	}
    
    public void FTPDownload() throws SocketException, IOException, JSchException, SftpException {
    	String sUser = "z001445";
    	String sHost = "10.76.60.21";
    	int iPort = 22;
    	String sPass = "Pr0pojin_2";
    	JSch jJSch = new JSch();
    	Session sSession = jJSch.getSession(sUser, sHost, iPort);
    	sSession.setConfig("StrictHostKeyChecking", "no");
    	sSession.setPassword(sPass);
    	sSession.connect();
    	System.out.println("Connected?? " + sSession.isConnected());
    	ChannelSftp csChannel=(ChannelSftp) sSession.openChannel("sftp");
    	csChannel.connect();
    	csChannel.cd("/infra_shared/ib/tmp/output_crm");
    	System.out.println("Current remote directory: " + csChannel.pwd());
    	
    	@SuppressWarnings("rawtypes")
    	Vector vLs = csChannel.ls("*");
    	List<String> sFilesList = new ArrayList<String>();
    	int iLsSize =vLs.size();
    	for(int i = 0; i<iLsSize; i++) {
    		String[] parts = vLs.get(i).toString().split(" ");
    		sFilesList.add(parts[parts.length-1]);
    	}
    	
    	for(String sAux : sFilesList) {
    		csChannel.get("/infra_shared/ib/tmp/output_crm/" + sAux, "/FTPFiles");
    	}
    	
    	csChannel.disconnect();
    	csChannel.disconnect();
    }
    
    public void deleteAllFiles() {
    	System.out.println("Delete time");
    	//List<String> sFiles = new ArrayList<String>();
		String sFolderName = "FTPFiles";
		File[] fListFiles = new File(sFolderName).listFiles();
		
		/*for (int i = 0; i < fListFiles.length; i++) {
			
			if (fListFiles[i].isFile()) {
				String fileName = fListFiles[i].getName();
				sFiles.add(fileName);
			}
		}*/
    	
		for(File fAux : fListFiles) {
			System.out.println("File name: " + fAux.getName());
			deleteFile(fAux.getName());
		}
    }
    
	public void decompressGzip(String sInput) throws IOException {
		String sInputComplete = "FTPFiles/" + sInput;
		String sOutput = "FTPFiles/" + sInput.replace(".gz", "");
		
		try (GZIPInputStream gisIn = new GZIPInputStream(new FileInputStream(sInputComplete))){
			try (FileOutputStream fosOut = new FileOutputStream(sOutput)){
				byte[] bBuffer = new byte[1024];
				int iLen;
				while((iLen = gisIn.read(bBuffer)) != -1){
					fosOut.write(bBuffer, 0, iLen);
				}
			}
		}
	}
	
	public List<String> findFiles(String sName) {
		List<String> sFiles = new ArrayList<String>();
		String sFolderName = "FTPFiles";
		File[] fListFiles = new File(sFolderName).listFiles();
		
		for (int i = 0; i < fListFiles.length; i++) {
			
			if (fListFiles[i].isFile()) {
				String fileName = fListFiles[i].getName();
				if (fileName.contains(sName)) {
					sFiles.add(fileName);
				}
			}
		}
		
		return sFiles;
	}
	
	public void deleteFile(String sFile) {
		File fFile = new File("FTPFiles/" + sFile);
		fFile.delete();
	}
	
	public boolean dateFormat(String sAux, String sDateFormat) throws ParseException {
		//sAux = String to compare to date format
		String sDate = sAux.split("_")[2].replaceAll(".txt", "");
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat(sDateFormat);
		Date dDate = sdfDateFormat.parse(sDate);
		
		boolean bAssert = dDate.toString().split(" ")[5].equals(sDate.substring(0, 4));
		
		return bAssert;
	}
	
	@SuppressWarnings("resource")
	public List<String> readTxt(String sName) throws IOException {
		List<String> sContent = new ArrayList<String>();
		String[] sSplit;
		File fFile = null;
		FileReader frFileReader = null;
		BufferedReader brBufferedReader = null;
		fFile = new File ("FTPFiles/" + sName);
		frFileReader = new FileReader (fFile);
		brBufferedReader = new BufferedReader(frFileReader);
		
		String sLine;
		while((sLine=brBufferedReader.readLine())!=null) {
			if (sLine.toString().contains("|")) {
				sSplit = sLine.split("\\|");
			}
			else {
				sSplit = sLine.split(" ");
			}
			for (int i = 0; i < sSplit.length; i++) {
				sContent.add(sSplit[i]);
			}
		}
		frFileReader.close();
		brBufferedReader.close();
		return sContent;
	}
	
	public void checkName(String sName) throws ParseException, IOException {
		List<String> sFiles = findFiles(sName);
		
		for(String sAux : sFiles) {
			if (sAux.contains(".gz")) {
				decompressGzip(sAux);
				deleteFile(sAux);
			}
		}
		System.out.println("Decompression finished.");
		
		sFiles = findFiles("sName");
		Pattern pRegularExpressionName = Pattern.compile("^" + sName);
		String sDateFormat = "yyyyMMddHHmmss";
		Pattern pRegularExpressionFormat = Pattern.compile(".txt$");
		for(String sAux : sFiles) {
			Matcher mMatch = pRegularExpressionName.matcher(sAux);
			Assert.assertTrue(mMatch.find());
			
			mMatch = pRegularExpressionFormat.matcher(sAux);
			Assert.assertTrue(mMatch.find());
			
			dateFormat(sAux, sDateFormat);
		}
		
		System.out.println("All titles date format are correct.");
	}
	
	public boolean verifyTextMaxSize(String sCell, int iSize) {
		boolean bAssert = false;
		
		int iSizeOfContent = sCell.length();
		bAssert = iSizeOfContent <= iSize;
		
		return bAssert;
	}
	
	public void verifyCUITCUIT(String sCUILCUIT, String sDNI) {
		String[] sSplit;
		sSplit = sCUILCUIT.split("-");
		int iType = Integer.parseInt(sSplit[0]);
		int iVerifyDigit = Integer.parseInt(sSplit[2]);
		
		if (iType == 20 || iType == 23 || iType == 24 || iType == 27 || iType == 30 || iType == 33 || iType == 34) {
			Assert.assertTrue(true);
		}
		
		Assert.assertTrue(sSplit[1].equals(sDNI));
		
		int iVerifyDigit2;
		iVerifyDigit2 = (Integer.parseInt(sSplit[0].substring(0, 1)) * 5) + ( Integer.parseInt(sSplit[0].substring(1, 2)) * 4);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(0, 1)) * 3);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(1, 2)) * 2);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(2, 3)) * 7);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(3, 4)) * 6);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(4, 5)) * 5);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(5, 6)) * 4);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(6, 7)) * 3);
		iVerifyDigit2+= (Integer.parseInt(sSplit[1].substring(7, 8)) * 2);
		
		iVerifyDigit2 = iVerifyDigit2 % 11;
		
		iVerifyDigit2 = 11 - iVerifyDigit2;
		
		switch(iVerifyDigit2) {
			case 11:
				iVerifyDigit2 = 0;
				break;
			case 10:
				System.out.println("Wrong CUIL/CUIT type.");
				Assert.assertTrue(false);
				break;
			default:
				break;
		}
		
		Assert.assertTrue(iVerifyDigit2 == iVerifyDigit);
	}
	
}