package cadastro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dados.Cliente;
import dados.Produto;
import dados.Venda;
import erros.SisVendasException;

public class Cadastro {

	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	public static ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	public static ArrayList<Venda> listaVendas = new ArrayList<Venda>();
	

	public static void incluirCliente(Cliente objCliente){
		listaClientes.add(objCliente);
	}

	public static void excluirCliente(Cliente objCliente){
		listaClientes.remove(objCliente);
	}

	public static Cliente pesqClienteCpf(String cpf) throws SisVendasException{
		for (Cliente objClientes : listaClientes) {
			if (objClientes.getCpf().equals(cpf)) {
				return objClientes;
			}
		}
		throw new SisVendasException("Não existe cliente para o CPF.");
	}

	public static ArrayList<Cliente> pesqClienteCod(int cod) throws SisVendasException{
		ArrayList<Cliente> resposta = new ArrayList<Cliente>();
		for (Cliente objClientes : listaClientes) {
			if (objClientes.getCodigo()==cod) {
				resposta.add(objClientes);
			}
		}
	
		if (resposta.size() > 0) {
			Collections.sort(resposta, new ClientePorNome());
			return resposta;	
		} else {
			throw new SisVendasException("Não existe cliente para o nome.");
		}
	}

	public static ArrayList<Cliente> pesqClienteNome(String nome) throws SisVendasException{
		ArrayList<Cliente> resposta = new ArrayList<Cliente>();
		for (Cliente objClientes : listaClientes) {
			if (objClientes.getNome().toUpperCase().contains(nome.toUpperCase())) {
				resposta.add(objClientes);
			}
		}
		// CLASSIFICAR A LISTA RESPOSTA PELO NOME DO SOCIO
		if (resposta.size() > 0) {
			Collections.sort(resposta, new ClientePorNome());
			return resposta;	
		} else {
			throw new SisVendasException("Não existe cliente para o nome.");
		}
	}

	public static void incluirProduto(Produto objProduto){
		listaProdutos.add(objProduto);
	}

	public static void excluirProduto(Produto objProduto){
		listaProdutos.remove(objProduto);
	}

	public static ArrayList<Produto> pesqProdutoCod(int cod) throws SisVendasException{
		ArrayList<Produto> resposta = new ArrayList<Produto>();
		for (Produto objProdutos : listaProdutos) {
			if (objProdutos.getCodigo()==cod) {
				resposta.add(objProdutos);
			}
		}
		if (resposta.size() > 0) {
			Collections.sort(resposta, new ProdutoPorNome());
			return resposta;	
		} else {
			throw new SisVendasException("Não existe produto para o código.");
		}	
	}

	public static ArrayList<Produto> pesqProdutoNome(String nome) throws SisVendasException{
		ArrayList<Produto> resposta = new ArrayList<Produto>();
		for (Produto objProdutos : listaProdutos) {
			if (objProdutos.getNome().toUpperCase().contains(nome.toUpperCase())) {
				resposta.add(objProdutos);
			}
		}
		// CLASSIFICAR A LISTA RESPOSTA PELO NOME DO SOCIO
		if (resposta.size() > 0) {
			Collections.sort(resposta, new ProdutoPorNome());
			return resposta;	
		} else {
			throw new SisVendasException("Não existe produto para o nome.");
		}
	}

	public static void incluirVenda(Venda objVendas ){
		listaVendas.add(objVendas);
	}

	public static void excluirVenda(Venda objVendas){
		listaVendas.remove(objVendas);
	}

	public static Venda pesqVendaCod(int cod) throws SisVendasException{
		for (Venda objVendas : listaVendas) {
			if (objVendas.getNumVenda()==cod) {
				return objVendas;
			}
		}
		throw new SisVendasException("Não existe venda para o código.");
	}

	public static ArrayList<Venda> pesqVendaCliente(Cliente cliente) throws SisVendasException{
		ArrayList<Venda> resposta = new ArrayList<Venda>();
		for (Venda objVendas : listaVendas) {
			if (objVendas.getCliente()==cliente) {
				resposta.add(objVendas);
			}
		}
		// CLASSIFICAR A LISTA RESPOSTA PELO NOME DO SOCIO
		if (resposta.size() > 0) {
			Collections.sort(resposta, new VendaPorNome());
			return resposta;	
		} else {
			throw new SisVendasException("Não existe venda para o nome.");
		}
	}

}

class ClientePorNome implements Comparator<Cliente> {

	@Override
	public int compare(Cliente o1, Cliente o2) {
		// TODO Auto-generated method stub
		return o1.getNome().compareTo(o2.getNome());
	}

}

class ProdutoPorNome implements Comparator<Produto> {

	@Override
	public int compare(Produto o1, Produto o2) {
		// TODO Auto-generated method stub
		return o1.getNome().compareTo(o2.getNome());
	}

}

class VendaPorNome implements Comparator<Venda> {

	@Override
	public int compare(Venda o1, Venda o2) {
		// TODO Auto-generated method stub
		return o1.getCliente().getNome().compareTo(o2.getCliente().getNome());
	}

}
