package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

@SuppressWarnings("serial")
public class Produto implements Serializable{
	private int codigo;
	private String nome;
	private double precoUnitario;
	private GregorianCalendar dataInclusao;
	private GregorianCalendar dataUltAlteracao;
	
	private static int seq=0;

	public Produto(String nome, double precoUnitario,
			GregorianCalendar dataInclusao, GregorianCalendar dataUltAlteracao) {
		codigo = ++seq;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.dataInclusao = dataInclusao;
		this.dataUltAlteracao = dataUltAlteracao;
	}

	public Produto(String nome, double precoUnitario,
			GregorianCalendar dataInclusao) {
		codigo = ++seq;
		this.nome = nome;
		this.precoUnitario = precoUnitario;
		this.dataInclusao = dataInclusao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
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
		Produto.seq = seq;
	}

	@Override
	public String toString() {
		return
				"Código: " + codigo + "\n" +
				"Produto: " + nome + "\n" +
				"Preço Unitário: R$ " + precoUnitario + "\n" +
				"Data de inclusão: " + LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy") + "\n" +
				"Data de alteração: " + (dataUltAlteracao==null?"Sem alteração.":LtpUtil.formatarData(dataUltAlteracao, "dd/MM/yyyy")) + "\n";
	}
}
