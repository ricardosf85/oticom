package br.com.study.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.Element;


import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.security.core.codec.Hex;

public class Utilitario {
	
	//classe utilitaria para outras operações como conversão de data

	public static String FMT_DATA = "dd/MM/yyyy";
	public static String FMT_HORA = "HH:mm:ss";
	public static String FMT_HORA_CURTA = "HH:mm";
	public static String FMT_DATA_HORA = FMT_DATA + " " + FMT_HORA;
	public static String FMT_DATA_HORA_CURTA = FMT_DATA + " " + FMT_HORA_CURTA;
	static Logger logger;
	public static String[] REPLACES = { "a", "e", "i", "o", "u", "c" };
	public static Pattern[] PATTERNS = null;

	static char f[] = { 32, 137, 239, 188, 102, 125, 221, 72, 212, 68, 81, 37,
			86, 237, 147, 149, 70, 229, 17, 124, 115, 207, 33, 20, 122, 143,
			25, 215, 51, 183, 138, 142, 146, 211, 110, 173, 1, 228, 189, 14,
			103, 78, 162, 36, 253, 167, 116, 255, 158, 45, 185, 50, 98, 168,
			250, 235, 54, 141, 195, 247, 240, 63, 148, 2, 224, 169, 214, 180,
			62, 22, 117, 108, 19, 172, 161, 159, 160, 47, 43, 171, 194, 175,
			178, 56, 196, 112, 23, 220, 89, 21, 164, 130, 157, 8, 85, 251, 216,
			44, 94, 179, 226, 38, 90, 119, 40, 202, 34, 206, 35, 69, 231, 246,
			29, 109, 74, 71, 176, 6, 60, 145, 65, 13, 77, 151, 12, 127, 95,
			199, 57, 101, 5, 232, 150, 210, 129, 24, 181, 10, 121, 187, 48,
			193, 139, 252, 219, 64, 88, 233, 96, 128, 80, 53, 191, 144, 218,
			11, 106, 132, 155, 104, 91, 136, 31, 42, 243, 66, 126, 135, 30, 26,
			87, 186, 182, 154, 242, 123, 82, 166, 208, 39, 152, 190, 113, 205,
			114, 105, 225, 84, 73, 163, 99, 111, 204, 61, 200, 217, 170, 15,
			198, 28, 192, 254, 134, 234, 222, 7, 236, 248, 201, 41, 177, 156,
			92, 131, 67, 249, 245, 184, 203, 9, 241, 0, 27, 46, 133, 174, 75,
			18, 93, 209, 100, 120, 76, 213, 16, 83, 4, 107, 140, 52, 58, 55, 3,
			244, 97, 197, 238, 227, 118, 49, 79, 230, 223, 165, 153, 59 };

	static char[] code;

	static final String caracteresCodigo = "1234567890ABCDEFGHIJKLMNOPQRSTUVXWYZ";
	static final String caracteresCodigoNumerico = "1234567890";

	public static String geraCodigo(int tamanhoCodigo) {
		Random geradorAleatorio = new Random();
		int indiceAleatorio;
		StringBuffer bufferCodigo = new StringBuffer();
		for (int i = 0; i < tamanhoCodigo; i++) {
			indiceAleatorio = geradorAleatorio.nextInt(caracteresCodigo
					.length());
			bufferCodigo.append(caracteresCodigo.charAt(indiceAleatorio));
		}
		return bufferCodigo.toString();
	}

	public static String geraCodigoNumerico(int tamanhoCodigo) {
		Random geradorAleatorio = new Random();
		int indiceAleatorio;
		StringBuffer bufferCodigo = new StringBuffer();
		for (int i = 0; i < tamanhoCodigo; i++) {
			indiceAleatorio = geradorAleatorio.nextInt(caracteresCodigoNumerico
					.length());
			bufferCodigo.append(caracteresCodigoNumerico
					.charAt(indiceAleatorio));
		}
		return bufferCodigo.toString();
	}

	public static String getResourceProperty(Object clazz,
			String nomeArquivoResource, String propriedade) {

		Properties props = new Properties();
		String resource = nomeArquivoResource;

		InputStream stream = clazz.getClass().getClassLoader()
				.getResourceAsStream(resource);

		try {
			props.load(stream);
			return props.getProperty(propriedade);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static double diferencaEmHoras(Date dataInicial, Date dataFinal) {
		double result = 0;
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24;
		long horasRestantes = (diferenca / 1000) / 60 / 60 % 24;
		return (diferencaEmDias * 24) + horasRestantes;
	}	

	public static double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
		double result = 0;
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		double diferencaEmMinutos = (diferenca / 1000) / 60;
		return diferencaEmMinutos;
	}	
	
	public static String getResourceProperty(String nomeArquivoResource,
			String propriedade) {

		Properties props = new Properties();

		try {
			props.load(new FileInputStream(new File(nomeArquivoResource)));
			return props.getProperty(propriedade);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public static void registrarLog(String mensagem, String nomeArquivo,
			Exception exception, Class<?> classe) {

		logger = classe != null ? Logger.getLogger(classe) : Logger
				.getRootLogger();

		BasicConfigurator.configure();
		Appender fileAppender;
		try {
			fileAppender = new FileAppender(new PatternLayout(
					PatternLayout.TTCC_CONVERSION_PATTERN), nomeArquivo + "_"
					+ removerCaracterDeString(formataData(new Date()), '/')
					+ ".log");
			logger.addAppender(fileAppender);
			if (exception == null)
				logger.info(formataData(new Date()) + " "
						+ formatarHora(new Time(new Date().getTime())) + " > "
						+ mensagem);
			else
				logger.error(formataData(new Date()) + " "
						+ formatarHora(new Time(new Date().getTime())) + " > "
						+ exception.getMessage(), exception);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String criptografarMD5(String texto) {

		try {
			MessageDigest mdTexto = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(mdTexto.digest(texto.getBytes()));

			//char[] encodeHex = Hex.encodeHex(hash.toByteArray());
			char[] encodeHex = Hex.encode(hash.toByteArray());
			return new String(encodeHex);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Verifica se o digito verificador e valido
	 * 
	 * @param strCpf
	 * @return
	 */
	static public boolean validaCPF(String strCpf) {
		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		if (strCpf.length() > 11 || strCpf.length() < 11)
			return false;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount))
					.intValue();

			// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
			// e assim por diante.
			d1 = d1 + (11 - nCount) * digitoCPF;

			// para o segundo digito repita o procedimento incluindo o primeiro
			// digito calculado no passo anterior.
			d2 = d2 + (12 - nCount) * digitoCPF;
		}
		;

		// Primeiro resto da divis�o por 11.
		resto = (d1 % 11);

		// Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
		// menos o resultado anterior.
		if (resto < 2)
			digito1 = 0;
		else
			digito1 = 11 - resto;

		d2 += 2 * digito1;

		// Segundo resto da divis�o por 11.
		resto = (d2 % 11);

		// Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
		// menos o resultado anterior.
		if (resto < 2)
			digito2 = 0;
		else
			digito2 = 11 - resto;

		// Digito verificador do CPF que est� sendo validado.
		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf
				.length());

		// Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		// comparar o digito verificador do cpf com o primeiro resto + o segundo
		// resto.
		return nDigVerific.equals(nDigResult);
	}

	public static String addTrim(String parametro) {

		if (!isVazio(parametro))
			return parametro.trim();
		else
			return "";

	}

	private static void checarDesformatacao(String parametro)
			throws ParseException {

		for (int posicao = 0; posicao < parametro.length(); posicao++) {

			if (!Character.isDigit(parametro.charAt(posicao)))
				throw new ParseException(
						"O VALOR PASSADO POSSUI CARACTER(ES) INVALIDO(S).", 0);

		}
	}

	public static boolean verificarDesformatacao(String parametro) {

		for (int posicao = 0; posicao < parametro.length(); posicao++) {

			if (!Character.isDigit(parametro.charAt(posicao)))
				return false;
		}

		return true;
	}

	public static Date addDia(Date data) {
		return new Date(data.getTime() + (1000 * 60 * 60 * 24));
	}

	public static Date addDias(Date data, Integer numeroDias) {
		return new Date(data.getTime() + ((1000 * 60 * 60 * 24) * numeroDias));
	}

	public static void checarNull(Object valor) throws ParseException {
		if (valor == null)
			throw new ParseException("VALOR NULO", 0);

	}

	public static void checarString(String parametro) throws ParseException {
		checarString(parametro, null);
	}

	private static void checarString(String parametro, String mensagem)
			throws ParseException {

		for (int posicao = 0; posicao < mensagem.length(); posicao++) {

			if (!Character.isDigit(parametro.charAt(posicao))) {
				if (mensagem != null)
					throw new ParseException(mensagem, 0);
				else
					throw new ParseException(
							"O VALOR PASSADO COMO PARAMETRO POSSUI CARACTER(ES) INVALIDO(S).",
							0);

			}

		}

	}

	public static String checkNullResult(String parametro) {

		if (parametro == null)
			return "NULO";
		else
			return parametro.trim();

	}

	public static boolean comparaAnoAnterior(String parametro) {

		GregorianCalendar data = new GregorianCalendar();

		String auxiliar = String.valueOf(data.get(Calendar.YEAR) - 1);

		if (auxiliar.equals(parametro))
			return true;
		else
			return false;

	}

	public static boolean comparaAnoAtual(String parametro) {

		GregorianCalendar data = new GregorianCalendar();

		String auxiliar = String.valueOf(data.get(Calendar.YEAR));

		if (auxiliar.equals(parametro))
			return true;
		else
			return false;

	}

	public static boolean comparaAnoPosterior(String parametro) {

		GregorianCalendar data = new GregorianCalendar();

		String auxiliar = String.valueOf(data.get(Calendar.YEAR) + 1);

		if (auxiliar.equals(parametro))
			return true;
		else
			return false;

	}

	public static boolean comparaHoraAtual(String parametro) {

		GregorianCalendar data = new GregorianCalendar();

		String auxiliar = String.valueOf(data.get(Calendar.HOUR_OF_DAY));

		if (auxiliar.equals(parametro))
			return true;
		else
			return false;

	}

	public static boolean comparaMesAtual(String parametro) {

		GregorianCalendar data = new GregorianCalendar();

		String auxiliar = String.valueOf(data.get(Calendar.MONTH - 1));

		if (auxiliar.equals(parametro))
			return true;
		else
			return false;

	}

	public static String desformataData(String parametro) {
		parametro = padR(parametro, 10);
		parametro = parametro.substring(0, 2) + parametro.substring(3, 5)
				+ parametro.substring(6, 10);

		return parametro;

	}

	public static String desformatarCNPJ(String parametro)
			throws ParseException {

		if (isVazio(parametro))
			throw new ParseException("CNPJ NAO INFORMADO.", 0);

		if (parametro.length() != 18)
			throw new ParseException("TAMANHO DO CNPJ INVALIDO", 0);

		String retorno = parametro.substring(0, 2) + parametro.substring(3, 6)
				+ parametro.substring(7, 10) + parametro.substring(11, 15)
				+ parametro.substring(16, 18);

		checarDesformatacao(retorno);

		return retorno;

	}

	public static String desformatarCPF(String parametro) throws ParseException {

		if (isVazio(parametro)) {
			throw new ParseException("CPF NÃO INFORMADO.", 0);
		}

		if (!contemMascaraCPF(parametro) && parametro.length() == 11) {
			return parametro;
		}

		if (parametro.length() != 14) {
			throw new ParseException("TAMANHO DO CPF INFORMADO INVÁLIDO", 0);
		}

		String retorno = parametro.substring(0, 3) + parametro.substring(4, 7)
				+ parametro.substring(8, 11) + parametro.substring(12, 14);

		checarDesformatacao(retorno);

		return retorno;

	}

	private static boolean contemMascaraCPF(String parametro) {

		if (parametro.contains(".") && parametro.contains("-")) {
			return true;
		}
		return false;
	}

	public static String desformatarCEP(String parametro) throws ParseException {

		if (isVazio(parametro))
			return parametro;

		if (parametro.length() != 9)
			return parametro;

		String retorno = parametro.substring(0, 5) + parametro.substring(6, 9);

		checarDesformatacao(retorno);

		return retorno;
	}

	public static String desformatarCep(String parametro) {

		String retorno = parametro.substring(0, 5) + parametro.substring(6, 9);

		return retorno;
	}

	public static String desformatarValor(String parametro) {

		String retorno = "";

		int x, y, tamanho;

		tamanho = parametro.length() + 1;

		if (parametro.indexOf(",") != -1) {

			for (x = 0, y = 1; y < tamanho; x++, y++) {
				if (parametro.substring(x, y).compareTo(".") == 0)
					continue;

				retorno = retorno + parametro.substring(x, y);

			}

			retorno = retorno.replace(',', '.');

		} else {
			retorno = parametro;

		}

		return (retorno);

	}

	public static String envolverString(String texto, String existente,
			String texto1, String texto2) {
		int len = existente.length();
		int pos = texto.indexOf(existente);
		int oldpos = 0;
		StringBuffer ret = new StringBuffer();
		for (; pos >= 0; pos = texto.indexOf(existente, oldpos)) {
			ret.append(texto.substring(oldpos, pos));
			ret.append(texto1);
			ret.append(texto.substring(pos, pos + len));
			ret.append(texto2);
			oldpos = pos + len;
		}

		ret.append(texto.substring(oldpos));
		return ret.toString();
	}

	/*
	 * public static String envolverStringSemCase(String texto, String
	 * existente, String texto1, String texto2) { int len = existente.length();
	 * int pos = filtrarAcentos(texto).toLowerCase().indexOf(
	 * filtrarAcentos(existente).toLowerCase()); int oldpos = 0; StringBuffer
	 * ret = new StringBuffer(); for (; pos >= 0; pos =
	 * filtrarAcentos(texto).toLowerCase().indexOf(
	 * filtrarAcentos(existente).toLowerCase(), oldpos)) {
	 * ret.append(texto.substring(oldpos, pos)); ret.append(texto1);
	 * ret.append(texto.substring(pos, pos + len)); ret.append(texto2); oldpos =
	 * pos + len; }
	 * 
	 * ret.append(texto.substring(oldpos)); return ret.toString(); }
	 */

	// public static String removeAcentos(String texto) {
	//
	// StringBuffer ret = new StringBuffer();
	// for (int pos = 0; pos < texto.length(); pos++) {
	// ret.append(filtrarAcentos(texto.charAt(pos)));
	// }
	//
	// return ret.toString();
	// }
	//
	 public static String removeAcentosEspacos(String texto) {
	
	 StringBuffer ret = new StringBuffer();
	 for (int pos = 0; pos < texto.length(); pos++) {
	 if (texto.charAt(pos) != ' ')
	 ret.append(filtrarAcentos(texto.charAt(pos)));
	 }
	
	 return ret.toString();
	 }
	/**
	 * Retira acentos de vogais e troca � por c, ou seja, retorna os caracteres
	 * correspondentes aos passados s� que sem os sinais ortogr�ficos. (Vers�o
	 * para tipo char). Data de cria��o: (21/08/2001 10:51:07)
	 * 
	 * @return char
	 * @param caractere
	 *            char
	 */
	 public static String filtrarAcentos(char caractere) {
	 char c = caractere;
	 char ret = ' ';
	
	 if (('a' == c) || ('á' == c) || ('à' == c) || ('ã' == c) || ('ä' == c) || ('â' == c)) {
	 ret = 'a';
	 } else if (('A' == c) || ('Á' == c) || ('À' == c) || ('Ã' == c)|| ('Ä' == c) || ('Â' == c)) {
	 ret = 'A';
	 } else if (('e' == c) || ('é' == c) || ('è' == c) || ('ë' == c) || ('ê' == c)) {
	 ret = 'e';
	 } else if (('E' == c) || ('É' == c) || ('È' == c) || ('Ë' == c) || ('Ê' == c)) {
	 ret = 'E';
	 } else if (('i' == c) || ('í' == c) || ('ì' == c) || ('ĩ' == c) || ('î' == c) || ('ï' == c)) {
	 ret = 'i';
	 } else if (('I' == c) || ('Í' == c) || ('Ì' == c) || ('Ĩ' == c) || ('Î' == c) || ('Ï' == c)) {
	 ret = 'I';
	 } else if (('o' == c) || ('ó' == c) || ('ò' == c) || ('õ' == c) || ('ô' == c) || ('ö' == c)) {
	 ret = 'o';
	 } else if (('O' == c) || ('Ó' == c) || ('Ò' == c) || ('Õ' == c) || ('Ô' == c) || ('Ö' == c)) {
	 ret = 'O';
	 } else if (('u' == c) || ('ú' == c) || ('ù' == c) || ('ũ' == c) || ('û' == c) || ('ü' == c)) {
	 ret = 'u';
	 } else if (('U' == c) || ('Ú' == c) || ('Ù' == c) || ('Ũ' == c) || ('Û' == c) || ('Ü' == c)) {
	 ret = 'U';
	 // } else if (('�' == c) || ('�' == c)) {
	 // ret = ' ';
	 } else if ('Ñ' == c) {
	 ret = 'N';
	 } else if ('ñ' == c) {
	 ret = 'n';
	 } else if ('ç' == c) {
	 ret = 'c';
	 } else if ('Ç' == c) {
	 ret = 'C';
	 } else if (' ' == c) {
	 return new String();
	 } else if ('/' == c || '\\' == c || ':' == c || '*' == c || '?' == c ||
	 '"' == c || '<' == c || '>' == c || '|' == c ){
	 // Adicionados caracteres especiais que n�o podem estar no path nem nonome de arquivos
	 return new String();
	 } else {
	 ret = c;
	 }
	
	 return String.valueOf(ret);
	 }
	/**
	 * Retira acentos de vogais e troca � por c. (Vers�o para tipos String)
	 * Retorna uma nova inst�ncia de String contendo o texto da String de
	 * entrada com os caracteres acentuados e o '�' trocados pelos seus
	 * correspondentes normais. Data de cria��o: (21/08/2001 10:51:07)
	 * 
	 * Exemplo:
	 * 
	 * Seja a chamada:
	 * 
	 * TratadorTexto.filtrarAcentos("N�s n�o comemos ma��.")
	 * 
	 * O retorno ser�:
	 * 
	 * "Nos nao comemos maca."
	 * 
	 * @return String
	 * @param texto
	 *            String
	 */
	// public static String filtrarAcentos(String texto) {
	// StringBuffer ret = new StringBuffer();
	// int i = 0;
	//
	// for (i = 0; i < texto.length(); i++)
	// ret.append(filtrarAcentos(texto.charAt(i)));
	//
	// return ret.toString();
	// }
	/**
	 * Retira acentos de vogais e troca � por c. Al�m disso esse m�todo retira
	 * espa�os duplicados. (Vers�o para tipos String) Retorna uma nova inst�ncia
	 * de String contendo o texto da String de entrada com os caracteres
	 * acentuados e o '�' trocados pelos seus correspondentes normais. Data de
	 * cria��o: (21/08/2001 10:51:07)
	 * 
	 * Exemplo:
	 * 
	 * Seja a chamada:
	 * 
	 * TratadorTexto.filtrarAcentos("N�s n�o comemos ma��.")
	 * 
	 * O retorno ser�:
	 * 
	 * "Nos nao comemos maca."
	 * 
	 * @return String
	 * @param texto
	 *            String
	 */
	// public static String filtrarAcentosEspacosRepetidos(String texto) {
	// StringBuffer ret = new StringBuffer();
	// int i = 0;
	// for (i = 0; i < texto.length(); i++)
	// ret.append(filtrarAcentos(texto.charAt(i)));
	// String result = ret.toString().trim();
	// while (result.indexOf("  ") != -1) {
	// result = ret.toString().replaceAll("  ", " ");
	// }
	// return result;
	// }
	public static String filtrarEspacosRepetidos(String texto) {
		String result = texto.toString().trim();
		while (result.indexOf("  ") != -1) {
			result = result.toString().replaceAll("  ", " ");
		}
		return result;
	}

	public static String formataData(String parametro) {

		if (!isVazio(parametro)) {
			parametro = padR(parametro, 8);
			parametro = parametro.substring(0, 2) + "/"
					+ parametro.substring(2, 4) + "/"
					+ parametro.substring(4, 8);

		}

		return parametro;

	}

	/**
	 * @param strDate
	 * @return
	 */
	public static Date parseData(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_DATA);

		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDataHora(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_DATA_HORA);

		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formataData(Date data) {
		java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat(
				FMT_DATA);
		return formatData.format(data);
	}

	public static String formataDataHoraCurta(Date data) {
		java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat(
				FMT_DATA_HORA_CURTA);
		return formatData.format(data);
	}

	public static String formataDataHora(Date data) {
		java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat(
				FMT_DATA_HORA);
		return formatData.format(data);
	}

	public static String formataData(String formato, Date data) {
		java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat(
				formato);
		return formatData.format(data);
	}

	public static String retornaDataAtual(String mascara) {

		Date date = new Date();
		java.text.SimpleDateFormat formatData = new java.text.SimpleDateFormat(
				mascara);
		return formatData.format(date);
	}

	public static String formataDataPorExtenso(String parametro)
			throws IOException {

		String dia = null;
		String mes = null;
		String ano = null;
		String retorno = null;

		if (!isVazio(parametro)) {

			if (parametro.length() == 8) {
				dia = parametro.substring(0, 2);
				mes = parametro.substring(2, 4);
				ano = parametro.substring(4, 8);

			} else if (parametro.length() == 10) {
				dia = parametro.substring(0, 2);
				mes = parametro.substring(3, 5);
				ano = parametro.substring(6, 10);

			} else {
				throw new IOException(
						"FORMATO DA DATA NAO LOCALIZADO EM FORMATADATAPOREXTENSO.");

			}

			int valor = Integer.parseInt(mes);

			switch (valor) {
			case 1:
				mes = "Janeiro";
				break;
			case 2:
				mes = "Fevereiro";
				break;
			case 3:
				mes = "Março";
				break;
			case 4:
				mes = "Abril";
				break;
			case 5:
				mes = "Maio";
				break;
			case 6:
				mes = "Junho";
				break;
			case 7:
				mes = "Julho";
				break;
			case 8:
				mes = "Agosto";
				break;
			case 9:
				mes = "Setembro";
				break;
			case 10:
				mes = "Outubro";
				break;
			case 11:
				mes = "Novembro";
				break;
			case 12:
				mes = "Dezembro";
				break;

			}

			retorno = dia + " de " + mes + " de " + ano;

		}

		return retorno;

	}

	public static String formatarCEP(String parametro) {

		String retorno = null;

		if (!isVazio(parametro)) {

			if (parametro.length() == 8) {
				retorno = parametro.substring(0, 5) + "-"
						+ parametro.substring(5, 8);

			} else {
				retorno = parametro;

				for (int i = parametro.length(); i < 8; i++)
					retorno = "0" + retorno;

				retorno = retorno.substring(0, 5) + "-"
						+ retorno.substring(5, 8);

			}

		}

		return retorno;

	}

	public static String formatarCNPJ(String parametro) {

		String retorno = null;

		if (!isVazio(parametro)) {

			if (parametro.length() == 14) {
				retorno = parametro.substring(0, 2) + "."
						+ parametro.substring(2, 5) + "."
						+ parametro.substring(5, 8) + "/"
						+ parametro.substring(8, 12) + "-"
						+ parametro.substring(12, 14);

			} else {
				retorno = parametro;

				for (int i = parametro.length(); i < 14; i++)
					retorno = "0" + retorno;

				retorno = retorno.substring(0, 2) + "."
						+ retorno.substring(2, 5) + "."
						+ retorno.substring(5, 8) + "/"
						+ retorno.substring(8, 12) + "-"
						+ retorno.substring(12, 14);

			}

		}

		return retorno;

	}

	public static String formatarCPF(String parametro) {

		String retorno = null;

		if (!isVazio(parametro)) {

			if (parametro.length() == 11) {
				retorno = parametro.substring(0, 3) + "."
						+ parametro.substring(3, 6) + "."
						+ parametro.substring(6, 9) + "-"
						+ parametro.substring(9, 11);

			} else {
				retorno = parametro;

				for (int i = parametro.length(); i < 11; i++)
					retorno = "0" + retorno;

				retorno = retorno.substring(0, 3) + "."
						+ retorno.substring(3, 6) + "."
						+ retorno.substring(6, 9) + "-"
						+ retorno.substring(9, 11);

			}

		}

		return retorno;

	}

	/**
	 * @param hora
	 * @return
	 */
	public static Time parseHora(String hora) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_HORA);

		try {
			return new Time(format.parse(hora).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param hora
	 * @return
	 */
	public static Time parseHoraCurta(String hora) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_HORA_CURTA);

		try {
			return new Time(format.parse(hora).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatarHora(String parametro) {

		String retorno = null;

		if (!isVazio(parametro))
			retorno = parametro.substring(11, 19);

		return retorno;
	}

	/**
	 * @param time
	 * @return
	 */
	public static String formatarHora(Time time) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_HORA);

		return format.format(time);
	}

	/**
	 * @param time
	 * @return
	 */
	public static String formatarHoraCurta(Date time) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_HORA_CURTA);

		return format.format(time);
	}

	public static String getEnderecoBaseDinamico(HttpServletRequest requisicao) {

		String endereco_ = null;
		String contexto_ = null;

		if (requisicao.getServerPort() == 443)
			endereco_ = "https://" + requisicao.getServerName();
		else
			endereco_ = "http://" + requisicao.getServerName();

		if (requisicao.getServerPort() != 80
				&& requisicao.getServerPort() != 443)
			endereco_ += ":" + requisicao.getServerPort();

		if (!Utilitario.isVazio(requisicao.getContextPath())
				&& !Utilitario.addTrim(requisicao.getContextPath()).equals("/"))
			contexto_ = Utilitario.addTrim(requisicao.getContextPath());

		return endereco_ + (contexto_ != null ? contexto_ : "");

	}

	public static String getEnderecoBaseEstatico(HttpServletRequest requisicao) {

		String endereco_ = null;

		if (requisicao.getServerPort() == 443)
			endereco_ = "https://" + requisicao.getServerName();
		else
			endereco_ = "http://" + requisicao.getServerName();

		if (requisicao.getServerPort() != 80
				&& requisicao.getServerPort() != 443)
			endereco_ += ":" + requisicao.getServerPort();

		return endereco_;

	}

	/**
	 * Insert the method's description here. Creation date: (2/8/2004 14:29:56)
	 * 
	 * @return java.lang.String
	 */
	public static java.lang.String getEscopo(String escopo) throws IOException {

		if (Utilitario.isVazio(escopo))
			throw new IOException("ESCOPO N�O INFORMADO.");

		if (escopo.equalsIgnoreCase("page"))
			return escopo;

		else if (escopo.equalsIgnoreCase("request"))
			return escopo;

		else if (escopo.equalsIgnoreCase("session"))
			return escopo;

		else if (escopo.equalsIgnoreCase("application"))
			return escopo;

		else if (escopo.equalsIgnoreCase("properties"))
			return escopo;

		else if (escopo.equalsIgnoreCase("todos"))
			return escopo;

		else
			return "page";

	}

	/**
	 * Insira a descri��o do m�todo aqui. Data de cria��o: (16/1/2003 14:09:52)
	 * 
	 * @param dados
	 *            org.w3c.dom.Element
	 * @param arquivo
	 *            java.lang.String
	 */
	public static void gravarArquivoXML(Element dados, String arquivo)
			throws IOException {

		PrintWriter print = new PrintWriter(new FileOutputStream(arquivo));
		print.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		print.write(dados.toString());
		print.flush();

	}

	public static boolean isUFValida(String parametro) {

		String matriz = "RS#SC#PR#SP#RJ#MG#ES#MS#MT#GO#TO#DF#BA#AL#SE#RN#PE#CE#MA#PI#PB#FN#AM#AC#PA#RO#RR#AP#rs#sc#pr#sp#rj#mg#es#ms#mt#go#to#df#ba#al#se#rn#pe#ce#ma#pi#pb#fn#am#ac#pa#ro#rr#ap";

		if (matriz.indexOf(parametro.toUpperCase()) == -1)
			return false;
		else
			return true;

	}

	public static boolean isVazio(String valor) {

		if (valor == null || (valor.trim()).length() == 0)
			return true;
		else
			return false;

	}
	
	public static boolean isVazio(Object[] obj) {

		if (obj == null || obj.length == 0) {
			return true;
		}

		return false;

	}	

	public static boolean isVazio(Object obj) {

		if (obj == null) {
			return true;
		}

		return false;

	}

	public static boolean isVazio(char valor) {

		Character character = new Character(valor);

		return isVazio(character.toString());

	}

	public static boolean isVazio(Collection<?> lista) {
		if (lista == null || lista.isEmpty()) {
			return true;
		}

		return false;
	}
	
	public static boolean isVazio(Map<?,?> mapa) {
		if (mapa == null || mapa.isEmpty()) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean isVazio(HttpServletRequest requisicao, String campo) {

		if (requisicao.getParameter(campo) instanceof String) {
			return (isVazio(requisicao.getParameter(campo)));

		} else if (requisicao.getAttribute(campo) instanceof String) {
			return (isVazio((String) requisicao.getAttribute(campo)));

		} else if (requisicao.getAttribute(campo) instanceof Collection) {
			Collection auxiliar = (Collection) requisicao.getAttribute(campo);

			if (auxiliar.isEmpty())
				return true;
			else
				return false;

		} else if (requisicao.getAttribute(campo) instanceof Hashtable) {
			Hashtable auxiliar = (Hashtable) requisicao.getAttribute(campo);

			if (auxiliar.isEmpty())
				return true;
			else
				return false;

		} else if (requisicao.getAttribute(campo) instanceof Element) {
			Element elemento = (Element) requisicao.getAttribute(campo);

			return (isVazio(elemento.toString()));

		} else {
			return true;

		}

	}

	@SuppressWarnings("unchecked")
	public static boolean isVazio(HttpSession sessao, String campo) {

		if (sessao.getAttribute(campo) instanceof String) {
			return (isVazio((String) sessao.getAttribute(campo)));

		} else if (sessao.getAttribute(campo) instanceof Collection) {
			Collection auxiliar = (Collection) sessao.getAttribute(campo);

			if (auxiliar.isEmpty())
				return true;
			else
				return false;

		} else if (sessao.getAttribute(campo) instanceof Hashtable) {
			Hashtable auxiliar = (Hashtable) sessao.getAttribute(campo);

			if (auxiliar.isEmpty())
				return true;
			else
				return false;

		} else if (sessao.getAttribute(campo) instanceof Element) {
			Element elemento = (Element) sessao.getAttribute(campo);

			return (isVazio(elemento.toString()));

		} else {
			return true;

		}

	}

	public static boolean isVazio(Date date) {

		if (date == null || (date.toString().trim()).length() == 0)
			return true;
		else
			return false;

	}

	public static boolean isVazio(Double valor) {

		if (valor == null || (valor.toString().trim()).length() == 0)
			return true;
		else
			return false;

	}

	public static boolean isVazio(Integer valor) {

		if (valor == null || (valor.toString().trim()).length() == 0)
			return true;
		else
			return false;

	}

	public static boolean isVazio(Long valor) {

		if (valor == null || (valor.toString().trim()).length() == 0)
			return true;
		else
			return false;

	}

	/**
	 * Retira de 'texto' os brancos do in�cio e do fim e os brancos excedentes
	 * entre as palavras. Este m�todo substitui os caracteres com c�digo menor
	 * ou igual /u0020 (espa�o em branco e caracteres de controle) de entre as
	 * palavras do texto por um �nico espa�o em branco e exclui as ocorr�ncias
	 * destes caracteres do in�cio e do fim do texto.
	 * 
	 * Exemplo:
	 * 
	 * Considere que \n significa uma quebra de linha ou seja, um caracter ASCII
	 * 13 seguido de um ASCII 10 (caracter unicode u000D seguido de u000A).
	 * 
	 * A chamada a seguir:
	 * 
	 * TratadorTexto.limparBrancosExcedentes("Ol� \n mundo novo.");
	 * 
	 * Ter� como retorno:
	 * 
	 * "Ol� mundo novo."
	 * 
	 * 
	 * Data de cria��o: (22/08/2001 14:30)
	 * 
	 * @return String
	 * @param texto
	 *            String;
	 */
	public static String limparBrancosExcedentes(String texto) {
		int i;
		char c;
		StringBuffer ret = new StringBuffer();
		String txt = texto.trim();

		if (txt == null)
			return "";
		if (txt == texto)
			txt = new String(texto);

		for (i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c > '\u0020') {
				ret.append(c);
			} else if (ret.length() > 0
					&& ret.charAt(ret.length() - 1) > '\u0020') {
				ret.append('\u0020');
			}
		}

		return ret.toString();
	}

	private static boolean matchFilenameMask(String filename, String mascara) {
		if (mascara == null || mascara.trim().equals("*")) {
			return true;
		}

		String nomeArq = null;
		String extensao = null;
		int index = filename.indexOf(".");

		if (index == -1) {
			nomeArq = filename;
		} else {
			nomeArq = filename.substring(0, index);
			extensao = filename.substring(index + 1);
		}

		String nomeArqMask = null;
		String extensaoMask = null;

		index = mascara.indexOf(".");
		if (index == -1) {
			extensaoMask = mascara;
		} else {
			nomeArqMask = mascara.substring(0, index);
			extensaoMask = mascara.substring(index + 1);
		}

		return (matchMask(nomeArq, nomeArqMask) && matchMask(extensao,
				extensaoMask));
	}

	// Checa se o alvo passado bate com a m�scara.

	private static boolean matchMask(String alvo, String mascara) {
		if ((mascara == null && alvo == null)
				|| (mascara.trim().equals("*") && alvo != null)) {
			return true;
		}

		if ((mascara != null && alvo == null)
				|| (mascara == null && alvo != null)) {
			return false;
		}

		// Agora, ser�o avaliadas as 3 possibilidades para a m�scara: asterisco
		// no INICIO, MEIO ou FIM.
		// Para isso, a m�scara ser� dividida de acordo com as apari��es do
		// asterisco, sendo que o m�ximo que se
		// pode ter �: [TEXTO][*]TEXTO[*][TEXTO].
		// Sendo assim, os tr�s pontos poss�veis para a apari��o de 'TEXTO' ser�
		// lan�ada no array de 3 posi��es abaixo.
		// A primeira posi��o diz respeito ao inicio da m�scara, ou seja, o alvo
		// deve come�ar com esta string.
		// A segunda posi��o dis respeito ao meio da m�scara, ou seja, o alvo
		// tem que ter esse texto.
		// E a terceira indica que o alvo deve terminar com a string em quest�o.
		String possibilidade[] = new String[3];
		int posicao = 0;

		for (StringTokenizer stringtokenizer = new StringTokenizer(mascara,
				"*", true); stringtokenizer.hasMoreElements();) {
			String token = stringtokenizer.nextToken();
			if (token.equals("*")) {
				posicao++;
			} else {
				possibilidade[posicao] = token;
			}
		}

		if (posicao >= 3) {
			throw new RuntimeException(
					"A m\341scara utilizada est\341 fora do padr\343o");
		}

		int inicio = 0;
		int fim = alvo.length();

		String inicioMask = possibilidade[0];
		String meioMask = possibilidade[1];
		String fimMask = possibilidade[2];

		// checa se o in�cio do 'alvo' bate com o in�cio da m�scara
		if (inicioMask != null) {
			if (!alvo.startsWith(inicioMask)) {
				return false;
			}
			inicio = inicioMask.length();
		}

		// checa se o fim do 'alvo' bate com o fim da m�scara
		if (fimMask != null) {
			if (!alvo.endsWith(fimMask)) {
				return false;
			}
			fim -= fimMask.length();
		}

		// checa possibilidade MEIO
		return ((meioMask == null) || (alvo.substring(inicio, fim).indexOf(
				meioMask) != -1));

	}

	/**
	 * Centraliza um objeto em um determinado espa�o de caracteres. Data de
	 * cria��o: (11/3/2003 12:11:32)
	 * 
	 * @return java.lang.String
	 * @param texto
	 *            java.lang.String
	 */
	public static String padC(String valor, int comprimento) {

		String retorno = " ";
		int mediaDiferenca = 0;
		if (comprimento > valor.length())
			mediaDiferenca = (int) (comprimento - valor.length()) / 2;

		if (!isVazio(valor)) {
			retorno = valor;

			for (int i = 0; i < mediaDiferenca; i++)
				retorno = " " + retorno;

			for (int i = 0; i < (comprimento - valor.length() - mediaDiferenca); i++)
				retorno = retorno + " ";

		}

		return retorno;
	}

	public static String padL(double valor, int comprimento) {

		String retorno = valor + "";

		if (comprimento > 0) {

			try {
				String dado = String.valueOf(valor);

				retorno = dado;

				for (int i = 0; i < comprimento - dado.length(); i++)
					retorno = retorno + " ";

			} catch (Exception e) {

			}

		}

		return retorno;

	}

	public static String padL(int valor, int comprimento) {

		String retorno = valor + "";

		if (comprimento > 0) {

			try {
				String dado = String.valueOf(valor);

				retorno = dado;

				for (int i = 0; i < comprimento - dado.length(); i++)
					retorno = retorno + " ";

			} catch (Exception e) {

			}

		}

		return retorno;

	}

	public static String padL(String valor, int comprimento) {

		String retorno = "";

		if (!isVazio(valor)) {
			retorno = valor;

			for (int i = 0; i < comprimento - valor.length(); i++)
				retorno = retorno + " ";

		}

		return retorno;

	}

	public static String padR(double valor, int comprimento) {

		String retorno = valor + "";

		if (comprimento > 0) {

			try {
				String dado = String.valueOf(valor);

				retorno = dado;

				for (int i = 0; i < comprimento - dado.length(); i++)
					retorno = " " + retorno;

			} catch (Exception e) {

			}

		}

		return retorno;

	}

	public static String padR(int valor, int comprimento) {

		String retorno = valor + "";

		if (comprimento > 0) {

			try {
				String dado = String.valueOf(valor);

				retorno = dado;

				for (int i = 0; i < comprimento - dado.length(); i++)
					retorno = " " + retorno;

			} catch (Exception e) {

			}

		}

		return retorno;

	}

	public static String padR(String valor, int comprimento) {

		String retorno = " ";

		if (!isVazio(valor)) {
			retorno = valor;

			for (int i = 0; i < comprimento - valor.length(); i++)
				retorno = " " + retorno;

		}

		return retorno;

	}

	public static String removeCaracterFromString(String valor, char dado) {

		String retorno = valor;

		for (int i = retorno.indexOf(dado); i != -1; i = retorno.indexOf(dado,
				i))
			retorno = retorno.substring(0, i) + retorno.substring(i + 1);

		return retorno;

	}

	public static String removeCaracterFromString(String valor, String dado) {

		String retorno = valor;

		for (int i = 0; i < dado.length(); i++)
			retorno = removeCaracterFromString(retorno, dado.charAt(i));

		return retorno;
	}

	public static String removerCaracterDeString(String valor, char dado) {

		String auxiliar = valor;

		for (int i = auxiliar.indexOf(dado); i != -1; i = auxiliar.indexOf(
				dado, i))
			auxiliar = auxiliar.substring(0, i) + auxiliar.substring(i + 1);

		return auxiliar;

	}

	public static String removerCaracterDeString(String valor, String dado) {

		String auxiliar = valor;

		for (int i = 0; i < dado.length(); i++)
			auxiliar = removeCaracterFromString(auxiliar, dado.charAt(i));

		return auxiliar;

	}

	/**
	 * Retira de 'texto' todas as ocorr�ncias dos caracteres presentes em
	 * 'caracteres'. Data de cria��o: (21/08/2001 18:50)
	 * 
	 * Veja as chamadas e os seus respectivos valores de retorno:
	 * 
	 * TratadorTexto.retirarCaracteres("Ol� mundo java!", "j�") > "Ol mundo ava"
	 * TratadorTexto.retirarCaracteres("Ol� mundo java!", " ") > "Ol�mundojava!"
	 * TratadorTexto.retirarCaracteres("Ol� mundo java!", "Ooa") > "l� mund jv!"
	 * TratadorTexto.retirarCaracteres("Ol� *mundo))) ja~va!", "~!>*") > "Ol�
	 * mundo java"
	 * 
	 * @return String
	 * @param texto
	 *            String;
	 * @param caracteres
	 *            String;
	 */
	public static String retirarCaracteres(String texto, String caracteres) {
		int i, j;
		StringBuffer ret = new StringBuffer();
		boolean inserir;

		for (i = 0; i < texto.length(); i++) {
			inserir = true;
			for (j = 0; j < caracteres.length() && inserir; j++)
				if (texto.charAt(i) == caracteres.charAt(j))
					inserir = false;
			if (inserir)
				ret.append(texto.charAt(i));
		}
		return ret.toString();
	}

	public static String strZero(String parametro, int comprimento) {

		String retorno = "";

		if (!isVazio(parametro) && comprimento >= 0) {

			retorno = addTrim(parametro);

			if (parametro.length() >= 0 && parametro.length() < comprimento) {

				for (int i = 0; i < comprimento - parametro.length(); i++)
					retorno = "0" + retorno;

			}

		}

		return retorno;

	}

	/**
	 * Este m�todo troca todas as refer�ncias a 'oldText' por 'newText'. Este
	 * m�todo foi criado para que se pudesse alterar todos os arquivos abaixo de
	 * um determinado diret�rio de s� uma vez.
	 * 
	 * @param diretorio
	 *            File apontando para o diret�rio raiz para a
	 *            pesquisa/substitui��o
	 * @param oldText
	 *            texto a ser substitu�dos
	 * @param newText
	 *            novo texto para o lugar de oldText
	 * @param pesquisaSubDir
	 *            vari�vel booleana para o caso de se desejar somente alterar no
	 *            diret�rio raiz
	 * @param qtdFilesChanged
	 *            array de uma posi��o (inicializada com zero) que retornar� a
	 *            quantidade de arquivos onde a altera��o foi realizada.
	 * 
	 */
	public static void substituirString(File dir, String oldText,
			String newText, boolean pesquisaSubDir, int qtdFilesChanged[])
			throws IOException, FileNotFoundException {
		substituirString(dir, oldText, newText, pesquisaSubDir, null,
				qtdFilesChanged);
	}

	/**
	 * Este m�todo troca todas as refer�ncias a 'oldText' por 'newText'. Este
	 * m�todo foi criado para que se pudesse alterar v�rios os arquivos abaixo
	 * de um determinado diret�rio de s� uma vez. Para isso, ele recebe, dentre
	 * os v�rios par�metros uma m�scara. Esta m�scara � utilizada para se fazer
	 * um filtro nos nomes dos arquivos a serem alterados e deve conter, no
	 * m�ximo, dois asteriscos, sendo que as possibilidades s�o:
	 * [texto][*][texto][*][texto]. As seguintes m�scaras, portanto, seriam
	 * v�lidas: *.jsp, *.html, index*.txt, *index*.jsp, *in*dex.jsp, in*de*x.jsp
	 * 
	 * 
	 * @param diretorio
	 *            File apontando para o diret�rio raiz para a
	 *            pesquisa/substitui��o
	 * @param oldText
	 *            texto a ser substitu�dos
	 * @param newText
	 *            novo texto para o lugar de oldText
	 * @param pesquisaSubDir
	 *            vari�vel booleana para o caso de se desejar somente alterar no
	 *            diret�rio raiz
	 * @param mascara
	 *            mascara utilizada na pesquisa dos arquivos onde ser�o
	 *            realizadas as substitui��es
	 * @param qtdFilesChanged
	 *            array de uma posi��o (inicializada com zero) que retornar� a
	 *            quantidade de arquivos onde a altera��o foi realizada.
	 * 
	 */
	public static void substituirString(File diretorio, String oldText,
			String newText, boolean pesquisaSubDir, String mascara,
			int qtdFilesChanged[]) throws IOException, FileNotFoundException {

		if (diretorio.isFile()
				&& matchFilenameMask(diretorio.getName(), mascara)) {

			FileInputStream fis = new FileInputStream(diretorio);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new byte[256];
			int qtdBytesLidos;

			while ((qtdBytesLidos = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, qtdBytesLidos);
			}

			fis.close();
			baos.close();

			String conteudo = new String(baos.toByteArray());
			conteudo = substituirString(conteudo, oldText, newText);
			FileOutputStream fos = new FileOutputStream(diretorio);
			fos.write(conteudo.getBytes());
			fos.close();
			qtdFilesChanged[0]++;

			System.out.print("file seached => " + diretorio);
			if ((new String(baos.toByteArray())).indexOf(oldText) != -1) {
				System.out.println(" (file changed)");
				qtdFilesChanged[0] += 1;
			} else {
				System.out.println("\n");
			}

		} else if (pesquisaSubDir && diretorio.isDirectory()) {
			String files[] = diretorio.list();
			for (int i = 0; i < files.length; i++) {
				substituirString(new File(diretorio, files[i]), oldText,
						newText, pesquisaSubDir, mascara, qtdFilesChanged);
			}
		}

	}

	/**
	 * Substitui em 'texto' todas as ocorrencias de 'existente' por 'nova'. Data
	 * de cria��o: (22/08/2001 14:30)
	 * 
	 * Veja as chamadas e os seus respectinvo retornos:
	 * 
	 * TratadorTexto.substituirString("Ol� mundo java!", "java", "cruel") > "Ol�
	 * mundo cruel!" TratadorTexto.substituirString("Ol� mundo java!", " ", "%")
	 * > "Ol�%mundo%java!" TratadorTexto.substituirString("Ol� mundo java!",
	 * "a", "ah") > "Ol� mundo jahvah!"
	 * 
	 * 
	 * @return String
	 * @param texto
	 *            String;
	 * @param existente
	 *            String;
	 * @param nova
	 *            String;
	 */
	public static String substituirString(String texto, String existente,
			String nova) {
		int len = existente.length();
		int pos = texto.indexOf(existente);
		int oldpos = 0;
		StringBuffer ret = new StringBuffer();

		while (pos >= 0) {
			ret.append(texto.substring(oldpos, pos));
			ret.append(nova);
			oldpos = pos + len;
			pos = texto.indexOf(existente, oldpos);
		}
		ret.append(texto.substring(oldpos));

		return ret.toString();
	}

	public static String substituirStringSemCase(String texto,
			String existente, String nova) {
		int len = existente.length();
		int pos = texto.toLowerCase().indexOf(existente.toLowerCase());
		int oldpos = 0;
		StringBuffer ret = new StringBuffer();
		for (; pos >= 0; pos = texto.toLowerCase().indexOf(
				existente.toLowerCase(), oldpos)) {
			ret.append(texto.substring(oldpos, pos));
			ret.append(nova);
			oldpos = pos + len;
		}

		ret.append(texto.substring(oldpos));
		return ret.toString();
	}

	/**
	 * Preenche espa�os vazios como se fosse uma tabula��o pontilhada.
	 * Observa��o: O "comprimento" passado como par�metro deve ser, no m�nimo, a
	 * quantidade de caracteres do par�metro "valor" + 2. Data de cria��o:
	 * (26/3/2003 12:11:32)
	 * 
	 * @return java.lang.String
	 * @param texto
	 *            java.lang.String
	 * @param comprimento
	 */
	public static String tabPonto(String valor, int comprimento) {

		String retorno = " ";
		int preenchimento = 0;

		if (valor != null && valor.length() > 0) {
			retorno = valor;
			preenchimento = comprimento - valor.length();
			for (int i = 0; i < preenchimento; i++) {
				if (i == (preenchimento - 2))
					retorno = retorno + ": ";
				else if (i < (preenchimento - 2))
					retorno = retorno + ".";
				else if (preenchimento == 1)
					retorno = retorno + ":";
			}
		}

		return retorno;

	}

	public static String tratarMensagemErro(String parametro) {

		if (!isVazio(parametro))
			return "\n<inicio-mensagem-erro>\n" + addTrim(parametro)
					+ "\n<fim-mensagem-erro>";
		else
			return "\n<inicio-mensagem-erro>\nMENSAGEM DE ERRO N�O INFORMADA\n<fim-mensagem-erro>";

	}

	public static String tratarMensagemErro(String mensagemErro,
			String pilhaErro) {

		String retorno = null;

		if (!isVazio(mensagemErro))
			retorno = "\n<inicio-mensagem-erro>\n" + addTrim(mensagemErro)
					+ "\n<fim-mensagem-erro>";
		else
			retorno = "\n<inicio-mensagem-erro>\nMENSAGEM DE ERRO N�O INFORMADA\n<fim-mensagem-erro>";

		if (!isVazio(pilhaErro))
			retorno += "\n<inicio-pilha-erro>\n" + addTrim(pilhaErro)
					+ "\n<fim-pilha-erro>";
		else
			retorno += "\n<inicio-pilha-erro>\nPILHA DE ERRO N�O INFORMADA\n<fim-pilha-erro>";

		return retorno;

	}

	/**
	 * Insert the method's description here. Creation date: (23/03/2001
	 * 09:24:22)
	 * 
	 * @return java.lang.String
	 * @param parametro
	 *            java.lang.String
	 */
	public static String tratarStringDB2(String parametro) {

		String retorno = null;
		String auxiliar = parametro.trim();

		boolean finaliza = false;

		if (auxiliar.length() <= 255) {
			retorno = "'" + auxiliar + "'";

		} else {
			while (auxiliar.length() > 255) {

				if (auxiliar.length() == 255) {
					retorno = retorno + "'" + auxiliar.substring(0, 254) + "'";
					finaliza = true;

				} else {
					retorno = retorno + "'" + auxiliar.substring(0, 254)
							+ "'||";

				}

				auxiliar = auxiliar.substring(254);

			}

			if (!finaliza)
				retorno = retorno + "'" + auxiliar + "'";

		}

		return retorno;

	}

	public static String trocaPontoPorVirgula(String parametro) {
		return parametro.replace('.', ',');

	}

	public static String trocaVirgulaPorPonto(String parametro) {
		return parametro.replace(',', '.');

	}

	public static boolean validarDDD(String parametro) {

		boolean retorno = true;

		try {
			double valor = Double.parseDouble(parametro);

			if (valor <= 0 || valor > 999)
				retorno = false;

		} catch (Exception e) {
			retorno = false;

		}

		return retorno;

	}

	public static boolean validarEmail(String parametro) {

		boolean retorno = true;

		if (isVazio(parametro)) {
			retorno = false;

		} else if (parametro.indexOf("@") <= 0) {
			retorno = false;

		} else if (parametro.substring(0, parametro.indexOf("@")).length() <= 0) {
			retorno = false;

		} else if (parametro.substring(parametro.indexOf("@") + 1).length() <= 0) {
			retorno = false;

		} else if (parametro.substring(parametro.indexOf("@") + 1).indexOf("@") > 0) {
			retorno = false;

		} else if ((parametro.substring(parametro.indexOf("@") + 1))
				.indexOf(".") <= 0) {
			retorno = false;

		} else if (parametro.substring(parametro.indexOf(".") + 1).length() <= 0) {
			retorno = false;

		} else if (parametro.substring(parametro.indexOf(".") + 1).length() > 0) {
			String auxiliar = parametro.substring(parametro.indexOf(".") + 1);

			if (auxiliar.length() > 1
					&& auxiliar.substring(auxiliar.indexOf(".") + 1).length() <= 0) {
				retorno = false;

			} else {
				while (auxiliar.length() > 1
						&& auxiliar.substring(auxiliar.indexOf(".") + 1)
								.length() > 0) {

					if (auxiliar.indexOf(".") > 0)
						auxiliar = auxiliar
								.substring(auxiliar.indexOf(".") + 1);
					else
						break;

				}

				if (auxiliar.indexOf(".") > 0)
					retorno = false;
				else
					retorno = true;

			}

		}

		return retorno;

	}

	public static boolean validarTelefone(String parametro) {

		boolean retorno = true;

		try {
			double valor = Double.parseDouble(parametro);

			if (valor <= 0)
				retorno = false;

		} catch (Exception e) {
			retorno = false;

		}

		return retorno;

	}

	/**
	 * Recebe data no format dd/mm/aaaa e valida o dia, m�s e ano.
	 * 
	 * @param data
	 * @return true or false
	 */
	public static boolean validaData(String data) {
		GregorianCalendar calendar = new GregorianCalendar();
		int dia = 0, mes = 0, ano = 0;
		String diaStr = data.substring(0, data.indexOf("/"));
		String mesStr = data.substring(data.indexOf("/") + 1, data
				.lastIndexOf("/"));
		String anoStr = data
				.substring(data.lastIndexOf("/") + 1, data.length());
		try {
			dia = Integer.parseInt(diaStr);
			mes = Integer.parseInt(mesStr);
			ano = Integer.parseInt(anoStr);
		} catch (Exception e) {
			return false;
		}
		// Verifica se o ano � maior que 1900
		if (ano < 1900)
			return false;

		if (dia < 1 || mes < 1 || ano < 1)
			return false;
		else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8
				|| mes == 10 || mes == 12)
			if (dia <= 31)
				return true;
			else
				return false;
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
			if (dia <= 30)
				return true;
			else
				return false;
		else if (mes == 2)
			if (calendar.isLeapYear(ano))
				if (dia <= 29)
					return true;
				else
					return false;
			else if (dia <= 28)
				return true;
			else
				return false;
		else if (mes > 12)
			return false;
		return true;
		/*
		 * GregorianCalendar calendar = new GregorianCalendar(); int dia = 0,
		 * mes = 0, ano = 0; if (!data.equals("")) { String diaStr =
		 * data.substring(0, 2); String mesStr = data.substring(3, 5); String
		 * anoStr = data.substring(6, 10); try { dia = Integer.parseInt(diaStr);
		 * mes = Integer.parseInt(mesStr); ano = Integer.parseInt(anoStr); }
		 * catch (Exception e) { return false; } if (dia < 1 || mes < 1 || ano <
		 * 1) return false; else if (mes == 1 || mes == 3 || mes == 5 || mes ==
		 * 7 || mes == 8 || mes == 10 || mes == 12) if (dia <= 31) return true;
		 * else return false; else if (mes == 4 || mes == 6 || mes == 9 || mes
		 * == 11) if (dia <= 30) return true; else return false; else if (mes ==
		 * 2) if (calendar.isLeapYear(ano)) if (dia <= 29) return true; else
		 * return false; else if (dia <= 28) return true; else return false;
		 * else if (dia <= 28) return true; else return false; // return true; }
		 * return true;
		 */
	}

	/**
	 * Recebe um objeto String e um �ndice da quebra da string, consequentemente
	 * ser� adicionado retic�ncias na String de retorno. Lembre-se que a
	 * contagem come�a do ZERO.
	 * 
	 * Exemplo:
	 * 
	 * String result = truncarString("ABCDE", 2);
	 * 
	 * result == "ABC..."
	 * 
	 * @param alvo
	 * @param indiceQuebra
	 * @return String
	 */
	public static String truncarString(String alvo, int indiceQuebra) {
		StringBuffer result = new StringBuffer(alvo);
		if (result.length() > indiceQuebra) {
			result = new StringBuffer(alvo.substring(0, indiceQuebra));
			result.append("...");
		}
		return result.toString();
	}

	/**
	 * Coloca em mai�scula um caracter da String fornecida de acordo com um
	 * index.
	 * 
	 * @param alvo
	 * @param index
	 * @return
	 */
	public static String transMaiuscula(String alvo, int index)
			throws StringIndexOutOfBoundsException {
		StringBuffer result = new StringBuffer(alvo);
		return result.replace(index, (index + 1),
				alvo.substring(index, (index + 1)).toUpperCase()).toString();
	}

	/**
	 * Coloca em min�scula um caracter da String fornecida de acordo com um
	 * index.
	 * 
	 * @param alvo
	 * @param index
	 * @return
	 */
	public static String transMinuscula(String alvo, int index)
			throws StringIndexOutOfBoundsException {
		StringBuffer result = new StringBuffer(alvo);
		return result.replace(index, (index + 1),
				alvo.substring(index, (index + 1)).toLowerCase()).toString();
	}

	/**
	 * Retorna a representa��o em <code>java.util.Date</code> do tempo de jogo.
	 * Espera-se um texto no formato mm'ss.
	 * 
	 * @param tempo
	 * @return
	 */
	public static Date parseTempoJogo(String tempo) {
		SimpleDateFormat format = new SimpleDateFormat("ss");

		try {
			return format.parse(tempo);
		} catch (ParseException ignored) {
			return null;
		}
	}

	/**
	 * Retorna um texto formato para mm'ss.
	 * 
	 * @param tempo
	 * @return
	 */
	public static String formatTempoJogo(Date tempo) {
		SimpleDateFormat format = new SimpleDateFormat("mm''ss");

		return format.format(tempo);
	}

	/**
	 * Retorna uma lista com n�meros aleat�rios, baseado nos parametros
	 * recebidos
	 * 
	 * @param qtd
	 *            Quantidade de numeros que ser� retornado no List
	 * @param max
	 *            N�mero m�ximo que pode ser escolhido aleatoriamente.
	 * @return
	 */
	public static List<Integer> pickIntRandomNumbers(int qtd, int max) {
		Random random = new Random(new Date().getTime());
		List<Integer> numberList = new ArrayList<Integer>();
		if (max >= qtd)
			for (int i = 0; i < qtd; i++) {
				int n = -1;
				do {
					n = random.nextInt(max);
				} while (numberList.contains(n));

				numberList.add(n);
			}
		return numberList;
	}

	public static Date addHora(Date data, Time hora) {

		String dataTemp = formataData(data);
		String horaTemp = formatarHora(hora);

		return parseDataHora(dataTemp + " " + horaTemp);
	}

	public static String decodeHexString(String hexText) {

		String decodedText = null;
		String chunk = null;

		if (hexText != null && hexText.length() > 0) {
			int numBytes = hexText.length() / 2;

			byte[] rawToByte = new byte[numBytes];
			int offset = 0;

			for (int i = 0; i < numBytes; i++) {
				chunk = hexText.substring(offset, offset + 2);
				offset += 2;
				rawToByte[i] = (byte) (Integer.parseInt(chunk, 16) & 0x000000FF);
			}
			// decodedText = new String(rawToByte);
			decodedText = rawToByte.toString(); // TODO VER SE É VAZIA
		}
		return decodedText;
	}

	public static String encodeHexString(String sourceText) {

		byte[] rawData = sourceText.getBytes();
		StringBuffer hexText = new StringBuffer();
		String initialHex = null;
		int initHexLength = 0;

		for (int i = 0; i < rawData.length; i++) {
			int positiveValue = rawData[i] & 0x000000FF;
			initialHex = Integer.toHexString(positiveValue);
			initHexLength = initialHex.length();
			while (initHexLength++ < 2) {
				hexText.append("0");
			}
			hexText.append(initialHex);
		}
		return hexText.toString();
	}

	public static String retornaTextoException(Exception exception) {

		String s = new String();

		if (exception != null) {
			StringWriter saida = new StringWriter();
			PrintWriter writer = new PrintWriter(saida);
			exception.printStackTrace(writer);

			s = saida.toString();
		}

		return s;
	}

	public static Integer retornaAnoEntreDatas(Date dataInicial, Date dataFinal) {
		// DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(dataInicial);
		cal2.setTime(dataFinal);
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		int month1 = cal1.get(Calendar.MONTH);
		int month2 = cal2.get(Calendar.MONTH);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		int idade = year2 - year1;
		if ((month2 < month1) || ((month2 == month1) && (day2 < day1))) {
			idade -= 1;
		}
		return new Integer(idade);
	}

	/**
	 * Metodo que verifica se a data final informada e menor que a data inicial
	 * informada.
	 * 
	 * @param dtInicio
	 * @param dtFim
	 * @return boolean
	 * @throws NullPointerException
	 */
	public static boolean validarDataInicioFim(Date dtInicio, Date dtFim)
			throws NullPointerException {

		if (!isVazio(dtInicio) && !isVazio(dtFim)) {
			if (dtFim.before(dtInicio))
				return false;
		} else {
			throw new NullPointerException("INFORME AS DUAS DATAS.");
		}

		return true;
	}

	// public static void main(String[] args) {
	//
	// String encrypt = Utilitario.encrypt("Gnt2010", "GRUPONT_SFW1234");
	//
	// String decrypt = Utilitario.decrypt(encrypt, "GRUPONT_SFW1234");
	//
	// String decrypt2 = Utilitario.decrypt(
	// "c2b5c3b8c2ac5ec2b817c3a91f0000000000000000000000000000000000",
	// "GRUPONT_SFW1234");
	//
	// System.out.println(encrypt);
	// System.out.println(decrypt);
	// System.out.println(decrypt2);
	//		
	//		
	// for (int i = 0; i < 10; i++) {
	// System.out.println(geraCodigo(8));
	// }
	// }

	/*
	 * @param valor = valor que sera arredondado
	 * 
	 * @param casas = numero de casas
	 * 
	 * @param useCeil = true arredonda para cima, false trunca o valor
	 */
	public static double arredondar(String valor, int casas, boolean useCeil) {
		BigDecimal bd = new BigDecimal(valor);
		return bd.setScale(casas,
				useCeil ? RoundingMode.CEILING : RoundingMode.FLOOR)
				.doubleValue();
	}

	/**
	 * diferencaEmDias = resultado é diferença entre as datas em dias
	 * horasRestantes = calcula as horas restantes result = transforma as horas
	 * restantes em fração de dias
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
		double result = 0;
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24;
		long horasRestantes = (diferenca / 1000) / 60 / 60 % 24;
		result = diferencaEmDias + (horasRestantes / 24d);
		return result;
	}

	public static String alfabeticoEntrePosicoes(int vl1, int vl2, String texto) {
		return texto.substring(vl1 - 1, vl2).trim();
	}

	public static String alfanumericoEntrePosicoes(int vl1, int vl2,
			String texto) {
		return texto.substring(vl1 - 1, vl2).trim();
	}

	public static String valorEntrePosicoes(int vl1, int vl2, String texto) {
		return String.copyValueOf(texto.toCharArray(), vl1, vl2);
	}

	public static String retornoEntrePosicoes(int vl1, int vl2, String texto) {
		return texto.substring(vl1 - 1, vl2).trim();
	}

	public static String retiraZerosAEsquerda(String string) {
		String resposta = string;
		while (resposta != null && resposta.length() > 0
				&& resposta.charAt(0) == '0') {
			resposta = resposta.substring(1);
		}
		return resposta;
	}

	public static Date dataEntrePosicoes(int vl1, int vl2, String texto)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
		return format.parse(texto.substring(vl1 - 1, vl2));
	}
	
	public static Date zeraHoraMinSegMili(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

	/**
	 * Formata uma string no formato monetario.
	 * 
	 * @param valor
	 * @param casasDecimais
	 * @return String O valor formatado no formato
	 *         [999.]999,99http://www.bb.com.
	 *         br/portalbb/home23,116,116,1,1,1,1.bb
	 */
	public static String formatarValorMonetario(String valor, int casasDecimais) {
		String resultado = "";
		if (isStringContemValor(valor)) {
			resultado = retiraZerosAEsquerda(valor);
			int tamanho = resultado.length();
			if (tamanho == 0) {
				resultado = "";
			} else if (tamanho <= casasDecimais) {
				resultado = "0,"
						+ completaComZerosAEsquerda(resultado, casasDecimais);
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append(resultado.substring(tamanho - casasDecimais))
						.reverse().append(',');
				int cursor = tamanho - (casasDecimais + 1);

				while (cursor >= 0) {
					for (int i = 0; i < (casasDecimais + 1) && cursor >= 0; i++, cursor--) {
						sb.append(resultado.charAt(cursor));
					}
					if (cursor >= 0) {
						sb.append('.');
					}
				}
				resultado = sb.reverse().toString();
			}
		}
		return resultado;
	}

	public static String formatDoubleJuros(double valor) {
		DecimalFormat f = new DecimalFormat("#,##0.0000");
		f.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt",
				"BR")));
		return f.format(valor / 10000);
	}

	public static String formatDoubleMoeda(double valor) {
		DecimalFormat f = new DecimalFormat("#,##0.00");
		f.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt",
				"BR")));
		if (valor == 0) {
			return f.format(valor);
		}
		return f.format(valor / 100);
	}

	/**
	 * Verifica se a string contem um valor (texto)
	 * 
	 * @param string
	 *            A string que sera testada
	 * @return true, se existir algum texto diferente de vazio na string, false
	 *         caso contrario
	 */
	public static boolean isStringContemValor(String string) {
		return string != null && !string.trim().equals("")
				&& !string.trim().equals("null");
	}

	public static String formataDoubleDuasCasasSemVirgula(Double valor) {
		DecimalFormat formatoDecimal = new DecimalFormat("###0.00");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		formatoDecimal.setDecimalFormatSymbols(symbols);
		return formatoDecimal.format(valor);
	}

	public static String formataDoubleQuatroCasasSemVirgula(Double valor) {
		DecimalFormat formatoDecimal = new DecimalFormat("###0.0000");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		formatoDecimal.setDecimalFormatSymbols(symbols);
		return formatoDecimal.format(valor);
	}

	/**
	 * Completa com zeros a esquerda, ate a string ter o tamanho tamanhoTotal
	 * 
	 * @param string
	 *            A string de entrada
	 * @param tamanhoTotal
	 *            O tamanho total da string resultate
	 * @return Uma nova string, com zeros a esquerda
	 */
	public static String completaComZerosAEsquerda(String string,
			int tamanhoTotal) {
		String resultado = "";
		if (isStringContemValor(string)) {
			for (int i = string.length(); i < tamanhoTotal; i++) {
				resultado = "0" + resultado;
			}
		}
		return resultado + string;
	}

	public static boolean ehMenorDeIdade(Date data) {

		if (retornaAnoEntreDatas(data,
				parseData(retornaDataAtual("dd/MM/yyyy"))) < 18) {
			return true;
		} else {
			return false;
		}

	}
	
	public static Date retornaMaior(Date date1, Date date2) {
		if (date1.after(date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	public static Date retornaMenor(Date date1, Date date2) {
		if (date1.before(date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	private static void compilePatterns() {
		PATTERNS = new Pattern[REPLACES.length];
		PATTERNS[0] = Pattern.compile("[âãáàä]", Pattern.CASE_INSENSITIVE);
		PATTERNS[1] = Pattern.compile("[éèêë]", Pattern.CASE_INSENSITIVE);
		PATTERNS[2] = Pattern.compile("[íìîï]", Pattern.CASE_INSENSITIVE);
		PATTERNS[3] = Pattern.compile("[óòôõö]", Pattern.CASE_INSENSITIVE);
		PATTERNS[4] = Pattern.compile("[úùûü]", Pattern.CASE_INSENSITIVE);
		PATTERNS[5] = Pattern.compile("[ç]", Pattern.CASE_INSENSITIVE);
	}

	public static String replaceSpecial(String text) {
		if (PATTERNS == null) {
			compilePatterns();
		}

		String result = text;
		for (int i = 0; i < PATTERNS.length; i++) {
			Matcher matcher = PATTERNS[i].matcher(result);
			result = matcher.replaceAll(REPLACES[i]);
		}
		return result;
	}

	/**
	 * Efetua o replace no StringBuffer informado. Atenção!! Não efetua o
	 * replaceAll. Somente efetua a substituição da primeria ocorrência de
	 * String encontrada no StringBuffer.
	 * 
	 * @param sb
	 *            StringBuffer do conteúdo.
	 * @param oldString
	 *            String a ser encontrada.
	 * @param newString
	 *            String a ser substituída.
	 */
	public static void replace(StringBuffer sb, String oldString,
			String newString) {
		int index = sb.indexOf(oldString);
		if (index >= 0) {
			sb.replace(index, (index + oldString.length()), newString);
		}
	}
	
	public static Date parseDataHoraCurta(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat(FMT_DATA_HORA_CURTA);
		
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Retira tags indesejadas, que influenciam na exibição do conteúdo pelo Internet Explorer
	 * @param texto
	 * @return
	 */
	public static String retirarTags(String texto){
		List<String> tags = new ArrayList<String>();
		
		tags.add("<w:View>Normal</w:View>");
		tags.add("<w:Zoom>0</w:Zoom>");
		tags.add("<w:HyphenationZone>21</w:HyphenationZone>");
		tags.add("<w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>");
		tags.add("<w:IgnoreMixedContent>false</w:IgnoreMixedContent>");
		tags.add("<w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>");
		tags.add("<w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>");
		
		
		for(String tag:tags){
			texto = texto.replaceAll(tag, "");
		}
				
		String tag = "<![^>]+>";
		String tag2= "<x[^>]+>";
		String tag3= "<w[^>]+>";
		String tag4= "</x[^>]+>";
		String tag5= "</w[^>]+>";
		String tag6= "<mce[^>]+>";
		
		texto = texto.replaceAll(tag, "")
						.replaceAll(tag2, "")
						.replaceAll(tag3, "")
						.replaceAll(tag4, "")
						.replaceAll(tag5, "")
						.replaceAll(tag6, "");
		
		return texto;
	}
	
	public static String gerarNuCodigoCertificado(){
		Long nuCodigoCertificado = 0L;
		do {
			nuCodigoCertificado = (long)(Math.random() * (Math.pow(10, 8)));
		} while (nuCodigoCertificado < 10000000);
		return nuCodigoCertificado.toString();
	}

}

