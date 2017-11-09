package dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

@SuppressWarnings("serial")
public class Venda implements Serializable{
	private int numVenda;
	private Cliente cliente;
	private GregorianCalendar dataVenda;
	private ArrayList<ItemVenda> vendaItens;

	private static int seq=0;

	public Venda(Cliente cliente, GregorianCalendar dataVenda,
			ArrayList<ItemVenda> vendaItens) {
		numVenda = ++seq;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.vendaItens = vendaItens;
	}

	public int getNumVenda() {
		return numVenda;
	}

	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public GregorianCalendar getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(GregorianCalendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public ArrayList<ItemVenda> getVendaItens() {
		return vendaItens;
	}

	public void setVendaItens(ArrayList<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}

	public static int getSeq() {
		return seq;
	}

	public static void setSeq(int seq) {
		Venda.seq = seq;
	}

	@Override
	public String toString() {		
		return
				"Código da Venda: " + numVenda + "\n" +
				"Cliente: " + cliente + "\n" +
				"Data da Venda: " + LtpUtil.formatarData(dataVenda, "dd/MM/yyyy") + "\n" +
				"Detalhes:\n"+ vendaItens +"\n";


	}

}
