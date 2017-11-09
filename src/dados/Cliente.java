package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

@SuppressWarnings("serial")
public class Cliente implements Serializable{
	private int codigo;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private GregorianCalendar dataInclusao;
	private GregorianCalendar dataUltAlteracao;
	
	private static int seq = 0;
	
	public Cliente(String cpf, String nome, String telefone,
			String email, GregorianCalendar dataInclusao) {
		codigo = ++seq;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataInclusao = dataInclusao;
	}
	
	public Cliente(String cpf, String nome, String telefone,
			String email, GregorianCalendar dataInclusao, GregorianCalendar dataUltAlteracao) {
		codigo = ++seq;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
	}
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GregorianCalendar getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(GregorianCalendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public GregorianCalendar getDataUltAlteracao() {
		return dataUltAlteracao;
	}

	public void setDataUltAlteracao(GregorianCalendar dataUltAlteracao) {
		this.dataUltAlteracao = dataUltAlteracao;
	}

	public static int getSeq() {
		return seq;
	}

	public static void setSeq(int seq) {
		Cliente.seq = seq;
	}

	public String toString() {
		return
				"Código: " + codigo + "\n" +
				"CPF: " + LtpUtil.formatarCPF(cpf) + "\n" +
				"Nome: " + nome + "\n" +
				"Data de inclusão: " + LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy") + "\n" +
				"Data de alteração: " + (dataUltAlteracao==null?"Sem alteração.":LtpUtil.formatarData(dataUltAlteracao, "dd/MM/yyyy")) + "\n";
	}	
	
	
}
