package Pages;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.Assert;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class remoteScriptExec {
	
	Session session;

    public void FTPConnection() {
        JSch jsch = new JSch();
        
        try {

            // Open a Session to remote SSH server and Connect.
            // Set User and IP of the remote host and SSH port.
            session = jsch.getSession("z001445", "10.76.60.21", 22);
            // When we do SSH to a remote host for the 1st time or if key at the remote host 
            // changes, we will be prompted to confirm the authenticity of remote host. 
            // This check feature is controlled by StrictHostKeyChecking ssh parameter. 
            // By default StrictHostKeyChecking  is set to yes as a security measure.
            session.setConfig("StrictHostKeyChecking", "no");
            //Set password
            session.setPassword("Pr0pojin_2");
            session.connect(30000);

            // create the execution channel over the session
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            // Set the command to execute on the channel and execute the command
            channelExec.setCommand("/infra_shared/ScriptsExe/S_REPL00/IC_REPLICA/referencia2valor.sh");
            channelExec.setCommand("cd /infra_shared/ScriptsExe/S_INTE00/IB_CRM/");
            channelExec.connect();

            // Get an InputStream from this channel and read messages, generated 
            // by the executing command, from the remote side.
            InputStream in = channelExec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Command execution completed here.

            // Retrieve the exit status of the executed command
            int exitStatus = channelExec.getExitStatus();
            if (exitStatus > 0) {
                System.out.println("Remote script exec error! " + exitStatus);
            }
            //Disconnect the Session
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        session.disconnect();
    }
    
    public void FTPDownloa() {
    	String serverAddress = "10.76.60.21"; // ftp server address 
        int port = 22; // ftp uses default port Number 21
        String username = "z001445";// username of ftp server
        String password = "Pr0pojin_2"; // password of ftp server
  
        FTPClient ftpClient = new FTPClient();
        try {
  
            ftpClient.connect(serverAddress, port);
            ftpClient.login(username,password);
 
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE/FTP.ASCII_FILE_TYPE);
            String remoteFilePath = "/filename.txt";
            File localfile = new File("E:/ftpServerFile.txt");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localfile));
            boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
            outputStream.close();
  
            if (success) {
                System.out.println("Ftp file successfully download.");
            }
  
        } catch (IOException ex) {
            System.out.println("Error occurs in downloading files from ftp Server : " + ex.getMessage());
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
   public void FTPDownload() throws SocketException, IOException, JSchException, SftpException {
	   String user = "z001445";
	   String host = "10.76.60.21";
	   int port = 22;
	   String pass = "Pr0pojin_2";
	   JSch jsch = new JSch();
	   Session session = jsch.getSession(user, host, port);
	   session.setConfig("StrictHostKeyChecking", "no");
	   session.setPassword(pass);
	   session.connect();
	   System.out.println("Connected?? " + session.isConnected());
	   ChannelSftp channel=(ChannelSftp) session.openChannel("sftp");
	   channel.connect();
	   channel.cd("/infra_shared/ib/tmp/output_crm");
       System.out.println("Current remote directory: " + channel.pwd());
       
       @SuppressWarnings("rawtypes")
       Vector ls = channel.ls("*");
       List<String> sFilesList = new ArrayList<String>();
       int iLsSize =ls.size();
       for(int i = 0; i<iLsSize; i++) {
	       String[] parts = ls.get(i).toString().split(" ");
	       sFilesList.add(parts[parts.length-1]);
       }
       
       for(String sAux : sFilesList) {
    	   channel.get("/infra_shared/ib/tmp/output_crm/" + sAux, "/FTPFiles");
       }
       
       channel.disconnect();
       session.disconnect();
    }
    
   public void FTPDownlo() throws java.net.MalformedURLException, java.net.UnknownHostException, java.io.IOException, java.io.FileNotFoundException {
		FTPClient ftpClient = new FTPClient();
		String host = "10.76.60.21";
		String userid = "z001445";
		String pwd = "Pr0pojin_2";
		ftpClient.connect(host);
		ftpClient.login(userid, pwd);
		System.out.println("Ftp is connected");

		URL hp = new URL("10.76.60.21//infra_shared//ib//tmp//output_crm");

		InputStream ip = hp.openStream();
		System.out.println("Ftp is connected thro URL ");

		// Make destination file
		try {
			FileOutputStream dest = new java.io.FileOutputStream("\\FTPFiles");

			byte[] buf = new byte[8192];
			System.out.println(buf);

			int byteCount;

			while ((byteCount = ip.read(buf)) > 0) {
				dest.write(buf, 0, byteCount);
			}
			dest.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void decompressGzip(String sInput) throws IOException {
		 String input = "FTPFiles/" + sInput;
		 String output = "FTPFiles/" + sInput.replace(".gz", "");
		
        try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input))){
            try (FileOutputStream out = new FileOutputStream(output)){
                byte[] buffer = new byte[1024];
                int len;
                while((len = in.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }
            }
        }
    }
	
	public List<String> findFiles(String sName) {
		List<String> sFiles = new ArrayList<String>();
		String folderName = "FTPFiles";
		File[] listFiles = new File(folderName).listFiles();

		for (int i = 0; i < listFiles.length; i++) {

		    if (listFiles[i].isFile()) {
		        String fileName = listFiles[i].getName();
		        if (fileName.contains(sName)) {
		            sFiles.add(fileName);
		        }
		    }
		}
		
		return sFiles;
	}
	
	public void deleteFile(String sFile) {
		File file = new File("FTPFiles/" + sFile);
        file.delete();
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
		String[] sSeparado;
		File archivo = null;
		FileReader fr = null;
	    BufferedReader br = null;
		archivo = new File ("FTPFiles/" + sName);
	    fr = new FileReader (archivo);
	    br = new BufferedReader(fr);
	    
	    String linea;
	    while((linea=br.readLine())!=null) {
	    	if (linea.toString().contains("|")) {
	    		sSeparado = linea.split("\\|");
	    	}
	    	else {
	    		sSeparado = linea.split(" ");
	    	}
	    	for (int i = 0; i < sSeparado.length; i++) {
	    		sContent.add(sSeparado[i]);
	    	}
	    }
	    
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
	
	public void verificarCUITCUIT(String sCUILCUIT, String sDNI) {
		String[] sSeparado;
		sSeparado = sCUILCUIT.split("-");
		int iTipo = Integer.parseInt(sSeparado[0]);
		int iDigitoVerificador = Integer.parseInt(sSeparado[2]);
		
		if (iTipo == 20 || iTipo == 23 || iTipo == 24 || iTipo == 27 || iTipo == 30 || iTipo == 33 || iTipo == 34) {
			Assert.assertTrue(true);
		}
		
		Assert.assertTrue(sSeparado[1].equals(sDNI));
		
		int iDigitoVerificador2;
		iDigitoVerificador2 = (Integer.parseInt(sSeparado[0].substring(0, 1)) * 5) + ( Integer.parseInt(sSeparado[0].substring(1, 2)) * 4);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(0, 1)) * 3);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(1, 2)) * 2);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(2, 3)) * 7);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(3, 4)) * 6);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(4, 5)) * 5);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(5, 6)) * 4);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(6, 7)) * 3);
		iDigitoVerificador2+= (Integer.parseInt(sSeparado[1].substring(7, 8)) * 2);
		
		iDigitoVerificador2 = iDigitoVerificador2 % 11;
		
		iDigitoVerificador2 = 11 - iDigitoVerificador2;
		
		switch(iDigitoVerificador2) {
			case 11:
				iDigitoVerificador2 = 0;
				break;
			case 10:
				System.out.println("Tipo CUIL/CUIT incorrecto.");
				Assert.assertTrue(false);
				break;
			default:
				break;
		}
		
		Assert.assertTrue(iDigitoVerificador2 == iDigitoVerificador);
	}
	
}