package br.com.gerenciador.modelo;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Relatorio {
	
	private String path; //Caminho base

	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public Relatorio() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/gerenciador/jasper/";
	}
	
	
public File imprimir(List<Negociacao> negociacao) throws Exception	{
	String nomeRelatorio = "relatorio.pdf";
	JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Negociacao.jrxml");
	
	JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(negociacao));
	
	JasperExportManager.exportReportToPdfFile(print, this.path+"br/com/gerenciador/temp"+nomeRelatorio);
	
	return new File(this.path+"br/com/gerenciador/temp"+nomeRelatorio);
}
	
	public File geraListaContatos(List<Contato> contatosList) throws Exception{
		String nomeArquivo = "exportacaoClientes.csv";
		String caminho = this.path+"br/com/gerenciador/temp";
		caminho = caminho.substring(1, caminho.length());
		try{
			Path path = FileSystems.getDefault().getPath(caminho, nomeArquivo);
			Files.deleteIfExists(path);
		    FileWriter q = new FileWriter(this.path+"br/com/gerenciador/temp/"+nomeArquivo, true);   
		    String linha = "nome;fantasia;razao;tipoPessoa;cpfCnpj;tel;tel2;email;"
		    		+ "email2;cidade;estado;endereco;numero;bairro;complemento\n";
		    q.write(linha);
		    for(Contato contato : contatosList){
		    	linha = "";
		    	linha = contato.getNome()+";"+
						contato.getFantasia()+";"+
		    			contato.getRazao()+";"+
						contato.getTipoPessoa().getLabel()+";"+
		    			contato.getCpfCnpj()+";"+
						contato.getTel()+";"+
		    			contato.getTel2()+";"+
						contato.getEmail()+";"+
		    			contato.getEmail2()+";"+
						contato.getCidade().getNome()+";"+
		    			contato.getCidade().getUf()+";"+
						contato.getEndereco()+";"+
		    			contato.getNumero()+";"+
						contato.getBairro()+";"+
		    			contato.getComplemento()+"\n";
		    	q.write(linha); // armazena o texto no objeto x, que aponta para o arquivo             
		    }
		    q.close(); // cria o arquivo              
		    return new File(this.path+"br/com/gerenciador/temp/"+nomeArquivo);
		}  
	    // em caso de erro apreenta mensagem abaixo  
	    catch(Exception e){
	    	System.out.println(e);
	    	return null;
	    }
	}
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
	
	public String getPath() {
		return this.path;
	}	
}
