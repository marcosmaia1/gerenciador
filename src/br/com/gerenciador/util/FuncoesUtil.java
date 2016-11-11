package br.com.gerenciador.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.hibernate.Session;

import br.com.gerenciador.enumeration.Operacao;

public class FuncoesUtil implements Serializable {

	private static final long serialVersionUID = 4643052577823198942L;
	
	public static String retornaDataHoraAtual() {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	}

	public static String formataDataHora(Date data, String formato) {
		return new SimpleDateFormat(formato).format(data);
	}

	public static String formataMensagemErro(String mensagem) {
		String retorno = null;

		if (mensagem.indexOf('[') >= 0) {
			retorno = mensagem.substring(mensagem.indexOf('[') + 1, mensagem.indexOf(']'));
		} else
			retorno = mensagem;

		return retorno;
	}

	public static String copiaTexto(String texto, int posicaoInicial, int posicaoFinal) {
		return texto.substring(posicaoInicial, posicaoFinal);
	}

	public static String copy(String texto, int posicaoInicial, int quantCaracter) {
		int i = posicaoInicial - 1;
		if (i < 0)
			i = 0;

		int total = (i + (quantCaracter - 1));
		if (total > texto.length())
			total = texto.length() - 1;

		String retorno = "";

		while (i <= total) {
			retorno = retorno + texto.charAt(i);
			i++;
		}

		return retorno;
	}

	public static String retornaSomenteNumeros(String s) {
		if (s == null) {
			return null;
		}
		return s.trim().replaceAll("[^0-9]+", "");
	}

	public static int strToInt(String valor) {
		return Integer.parseInt(valor);
	}

	public static String intToStr(int valor) {
		return String.valueOf(valor);
	}

	public static String intToStr(long valor) {
		return String.valueOf(valor);
	}

	public static int strToIntDef(String valor, int valorDefault) {
		try {
			return Integer.parseInt(valor);
		} catch (Exception e) {
			return valorDefault;
		}
	}
	
	public static BigDecimal strToBigDecimal(String valor) {
		return  new BigDecimal(valor);
}

public static String bigDecimalToStr(BigDecimal valor) {
	return String.valueOf(valor.doubleValue());
}

public static BigDecimal strToBigDecimalDef(String valor, BigDecimal valorDefault) {
	try {
		return  new BigDecimal(valor);
	} catch (Exception e) {
		return valorDefault;
	}
}
	

	public static Long strToLong(String valor) {
		return Long.parseLong(valor);
	}

	public static String longToStr(Long valor) {
		
		return valor == null ? "" : String.valueOf(valor);
	}
	
	public static Long strToLongDef(String valor, Long valorDefault) {
		try {
			return Long.parseLong(valor);
		} catch (Exception e) {
			return valorDefault;
		}
	}
	
	/**
	 * Se o String for nulo, retorna "". Caso contrï¿½rio, retorna o prï¿½prio
	 * String.
	 * 
	 * @param s
	 * @return
	 */
	public static String strNotNull(String s) {
		if (s == null) {
			s = "";
		}
		return s;
	}

	/**
	 * Retorna <i>true</i> se o String for nulo ou vazio.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean empty(String s) {
		return (s == null) || ("".equals(s.trim()));
	}

	/**
	 * Completa o String, completando com caracteres {@code ch} ï¿½ esquerda
	 * atï¿½ atingir o tamanho {@code len}.
	 * 
	 * @param s
	 * @param len
	 * @param ch
	 * @return
	 */
	public String padLeft(String s, int len, final char ch) {
		s = s.trim();
		StringBuilder sb = new StringBuilder();
		for (int i = s.length(); i < len; i++) {
			sb.append(ch);
		}
		sb.append(s);
		return sb.toString();
	}

	/**
	 * Completa o String, completando com caracteres {@code ch} ï¿½ direita
	 * atï¿½ atingir o tamanho {@code len}.
	 * 
	 * @param s
	 * @param len
	 * @param ch
	 * @return
	 */
	public String padRight(String s, int len, final char ch) {
		s = s.trim();
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		for (int i = s.length(); i < len; i++) {
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * Completa o String, completando com caracteres '0' ï¿½ esquerda atï¿½
	 * atingir o tamanho {@code len}.
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public String strZero(String s, int len) {
		return padLeft(s, len, '0');
	}

	/**
	 * Completa o nï¿½mero com zeros ï¿½ esquerda atï¿½ completar o nï¿½mero de
	 * dï¿½gitos especificados. Se o nï¿½mero possuir um nï¿½mero de dï¿½gitos
	 * maior do que o especificado, serï¿½ mantido como estï¿½.
	 * 
	 * @param n
	 * @param digitos
	 * @return
	 */
	public String strZero(long n, int digitos) {
		StringBuilder sb = new StringBuilder();
		String numero = "" + n;
		for (int i = numero.length(); i < digitos; i++) {
			sb.append('0');
		}
		sb.append(numero);
		return sb.toString();
	}

	/**
	 * Retorna os ï¿½ltimos N caracteres do String.
	 * 
	 * @param s
	 * @param tamanho
	 * @return
	 */

	public String retornaUltimosCaracteres(final String s, final int tamanho) {
		return s == null ? null : s.length() <= tamanho ? s : s.substring(s.length() - tamanho);
	}

	public static String removeAcentos(String texto) {
		texto = texto.toUpperCase();
		texto = texto.replaceAll("[�����]", "A");
		texto = texto.replaceAll("[����]", "E");
		texto = texto.replaceAll("[����]", "I");
		texto = texto.replaceAll("[����֖]", "O");
		texto = texto.replaceAll("[����]", "U");
		texto = texto.replaceAll("[�]", "C");
		texto = texto.replaceAll("[ё]", "N");
		texto = texto.replaceAll("[ݟ]", "Y");
		texto = texto.replaceAll("[\"\']","");

		return texto;
	}

	public static Date strToDate(String data) {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date strToDateTime(String data) {
		if (data == null || data.equals(""))
			return null;

		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = (java.util.Date) formatter.parse(data);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static void apagarAquivo(String caminho) {
		// Se o arquivo passado for um diretório
		File f = new File(caminho);

		if (f.isDirectory()) {
			/* Lista todos os arquivos do diretório em um array de objetos File */
			File[] files = f.listFiles();
			// Identa a lista (foreach) e deleta um por um
			for (File file : files) {
				file.delete();
			}
		}
	}

	// Função verifica se a data é válida
	public static boolean isData(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		try {
			df.parse(date);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}
	
	public static String caminhoBDGerencial(){
		ResourceBundle bundle = ResourceBundle.getBundle("vbc");
		return bundle.getString("caminho.bd.gerencial");
	}

	public static String caminhoBDPista(){
		ResourceBundle bundle = ResourceBundle.getBundle("vbc");
		return bundle.getString("caminho.bd.pista");
	}

	public static String pathAplicacaoAbsoluteProduction(){
		ResourceBundle bundle = ResourceBundle.getBundle("production");
		String path = bundle.getString("path.aplicacao.absolute");
		if (empty(path)){
			path = System.getProperty("user.home");
		}
		return path;
	}

	public static String pathAplicacaoProduction(){
		ResourceBundle bundle = ResourceBundle.getBundle("production");
		return bundle.getString("path.aplicacao");
	}

	public static String versaoAplicacaoProduction(){
		ResourceBundle bundle = ResourceBundle.getBundle("production");
		return bundle.getString("versao.aplicacao");
	}

	public static String nomeAplicacaoProduction(){
		ResourceBundle bundle = ResourceBundle.getBundle("production");
		return bundle.getString("nome.aplicacao");
	}
	
	public static String decoderText(String text) {
		try {
			byte[] bytes = text.getBytes("ISO-8859-1");
			text = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return text;
		}

		return text;
	}

	public static String encrypt(String text) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(text.getBytes("UTF-8"));

			StringBuilder hexBuild = new StringBuilder();

			for (byte b : messageDigest) {
				hexBuild.append(String.format("%02X", 0xFF & b));
			}

			return hexBuild.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return text;
	}

	public static String i18n(String text) {
		try {
			return ResourceBundle.getBundle("messages", Locale.getDefault()).getString(text);
		} catch (MissingResourceException e) {
			return "???" + text + "???";
		}
	}
	
    public static Timestamp dataHoraAtual()
    {
    	Date data = new Date();
    	return  new Timestamp(data.getTime());
    }
	
    public static String gerarMD5(String texto) throws NoSuchAlgorithmException{
    	MessageDigest m;
		m = MessageDigest.getInstance("MD5");
		m.update(texto.getBytes(),0,texto.length());
		return new BigInteger(1,m.digest()).toString(16);    	
    }
    
    public static String BlobToStr(byte[] blob){    	
        String valor = "";
		try{
			valor = new String(blob, "ISO-8859-1");
		}catch (UnsupportedEncodingException e) {
			//logger.error("Ocorreu um erro no processo de convers�o: "+e.getMessage());			
		}  
        return valor;    	    
    }
    
	public static String gerarDetalhamentoErro(Exception erro){
		String retorno = "";
		ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		erro.printStackTrace(printStream);
		try {
			retorno = outputStream.toString("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			retorno = erro.toString();
		}
		return retorno;
	}
	
	
	public static String formatarNumero(Double valor, String pattern){
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String retorno = decimalFormat.format(valor);
		return retorno;
	}

	public static boolean isCPF (String CPF) { 

		CPF = retornaSomenteNumeros(CPF);
		
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || 
			CPF.equals("22222222222") || CPF.equals("33333333333") || 
			CPF.equals("44444444444") || CPF.equals("55555555555") || 
			CPF.equals("66666666666") || CPF.equals("77777777777") || 
			CPF.equals("88888888888") || CPF.equals("99999999999") || 
		   (CPF.length() != 11)) 
			return(false); 
		
		char dig10, dig11; 
		int sm, i, r, num, peso; 
		
		try { 
			sm = 0; 
			peso = 10; 
			for (i=0; i<9; i++) { 
				num = (int)(CPF.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso - 1; 
			}
			
			r = 11 - (sm % 11); 
			if ((r == 10) || (r == 11)){ 
				dig10 = '0'; 
			}else{ 
				dig10 = (char)(r + 48);
				}
			
			sm = 0; 
			peso = 11; 
			for(i=0; i<10; i++) { 
				num = (int)(CPF.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso - 1; 
			} 
			
			r = 11 - (sm % 11); 
			if ((r == 10) || (r == 11)) 
				dig11 = '0'; 
			else 
				dig11 = (char)(r + 48); 
			
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) 
				return(true); 
			else 
				return(false); 
			} catch (InputMismatchException erro){ 
				return(false); 
			} 
		} 
	
	public static String formatarCPF(String CPF) { 
		return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11)); 
	}
	
	
	public static boolean isCNPJ(String CNPJ) {

		CNPJ = retornaSomenteNumeros(CNPJ);
		
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || 
			CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || 
			CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || 
			CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || 
			CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || 
		   (CNPJ.length() != 14)) 
			return(false); 
		
		char dig13, dig14; 
		int sm, i, r, num, peso; 
		try { 
			sm = 0; peso = 2; 
			for (i=11; i>=0; i--) 
			{ 
				num = (int)(CNPJ.charAt(i) - 48); 
				sm = sm + (num * peso); 
				peso = peso + 1; 
				if (peso == 10) 
					peso = 2; 
			} 
			
			r = sm % 11; 
			if ((r == 0) || (r == 1)) 
				dig13 = '0'; 
			else 
				dig13 = (char)((11-r) + 48); 
			
			sm = 0; peso = 2; 
			for (i=12; i>=0; i--) { 
				num = (int)(CNPJ.charAt(i)- 48); 
				sm = sm + (num * peso); 
				peso = peso + 1; 
				if (peso == 10) 
					peso = 2; 
			} 
			
			r = sm % 11; 
			if ((r == 0) || (r == 1)) 
				dig14 = '0'; 
			else 
				dig14 = (char)((11-r) + 48); 
			
			
			if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) 
				return(true); 
			else 
				return(false); 
		} catch (InputMismatchException erro) { 
			return(false); 
			} 
	} 
	
	public static String formatarCNPJ(String CNPJ) { 
		// m�scara do CNPJ: 99.999.999.9999-99 
		return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" + CNPJ.substring(12, 14)); 
	}
	
	public static boolean isEmail(String email)  
    {  
        boolean retorno = false;  
        if (email != null && email.length() > 0) {  
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
            Matcher matcher = pattern.matcher(email);  
            if (matcher.matches()) {  
            	retorno = true;  
            }  
        }  
        return retorno;  
    } 	
	
	public static boolean isURL(String email)  
    {  
		boolean retorno = false;  
        if (email != null && email.length() > 0) {  
        	String expression = "(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)";  
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);  
            Matcher matcher = pattern.matcher(email);  
            if (matcher.matches()) {  
            	retorno = true;  
            }  
        }  
        return retorno;  
    } 	
	
	public static Date dataToDataHora(Date data, String hora) {
	    String myDate = formataDataHora(data, "yyyy-MM-dd")  + " " + hora;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date utilDate = new Date();
	    try {
	        utilDate = sdf.parse(myDate);
	    } catch (ParseException pe){
	        pe.printStackTrace();
	    }

	    return utilDate;
	}

	public static String dateToDateTime(Date data, String hora) {
	    String myDate = formataDataHora(data, "yyyy-MM-dd")  + " " + hora;
	    return myDate;
	}
	
	public static String booleanToSimNao(Boolean valor){
		if (valor == null){
			return "N�o";
		}
		else{
			return (valor ? "Sim" : "N�o");
		}
	}
	
	public static String dateToStr(Date data){
		if (data != null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(data);
		}else{
			return "";
		}
	}

	
	public static String dateTimeToStr(Date data){
		if (data != null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return format.format(data);
		}else{
			return "";
		}
	}	
	
	public static Operacao retornaOperacao(int id){
		Operacao retorno;
		switch (id) {
		case 1:
			retorno = Operacao.EXIBINDO;
			break;
		case 2:
			retorno = Operacao.NOVO;
			break;
		case 3:
			retorno = Operacao.EDITAR;
			break;
		case 4:
			retorno = Operacao.EXCLUSAO;
			break;
		case 5:
			retorno = Operacao.PESQUISA;
			break;
		case 6:
			retorno = Operacao.RELATORIO;
			break;			
		case 7:
			retorno = Operacao.LOGIN;
			break;			
		case 8:
			retorno = Operacao.LOGOUT;
			break;
		case 9:
			retorno = Operacao.ALTERACAO_SENHA;
			break;
		case 10:
			retorno = Operacao.IMPORTACAO_DADOS;
			break;			
		case 11:
			retorno = Operacao.VALIDACAO;
			break;			
		default:
			retorno = null;
			break;
		}
		
		return retorno;
	}
	
    public static String lpad(String valor, String caracter, int size) {  
        while (valor.length() < size) {  
        	valor = caracter + valor;  
        }  
        return valor;  
    }  
      
    public static String rpad(String valor, String caracter, int size) {  
        String retorno = "";
    	if(valor.length() >= size){
            int contador = 1;
    		while (retorno.length() < size) {  
	    		retorno = retorno + copy(valor, contador, 1);
	    		contador++;
	        }  
    	}else{
	    	retorno = valor;
    		while (retorno.length() < size) {  
    			retorno = retorno + caracter;  
	        }  
    	}
        return retorno;  
    }
    
    public static Boolean existeRestricaoChaveEstrangeira(Exception e){
    	Boolean retorno = false;
    	
    	while (e.getCause() != null) {
    		e = (Exception) e.getCause();
			if(e instanceof java.sql.BatchUpdateException){
				java.sql.BatchUpdateException a = (java.sql.BatchUpdateException) e;
				if (a.getNextException().getMessage().contains("violates foreign key constraint")){
					retorno = true;
					break;
				}
			}	
    	}
    	
    	return retorno;
    }
	
	public static int retornaNumLetras(String texto, char letra){
		int numeroVezes = 0;		
		
		for(int index = 0; index < texto.length(); index++){			
			if (texto.charAt(index) == letra){
				numeroVezes++;
			}
		}
		
		return numeroVezes;
	}
	
	public static String formatarMascara(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static int retornaAno(Date data){
		int retorno = 0;
		if(data != null){
			retorno = strToIntDef(formataDataHora(data, "yyyy"), 0);
		}
		return retorno;
	}

	public static int retornaMes(Date data){
		int retorno = 0;
		if(data != null){
			retorno = strToIntDef(formataDataHora(data, "MM"), 0);
		}
		return retorno;
	}
	
	public static String retornaExtensaoArquivo(String nomeArquivo){
	    String retorno = "";
	    int pos = nomeArquivo.lastIndexOf('.');

	    if (pos > -1) {
	       retorno =  nomeArquivo.substring(pos + 1);
	    }

	    return retorno;
	  }
	
	public static void iniciaTransacao(Session session){
		cancelaTransacao(session);
		session.getTransaction().begin();		
	}
	
	public static void comitaTransacao(Session session){
		if (session.getTransaction().isActive()){			
			session.getTransaction().commit();
		}
	}
	
	public static void cancelaTransacao(Session session){
		if (session.getTransaction().isActive()){			
			session.getTransaction().rollback();
		}
	}
}
