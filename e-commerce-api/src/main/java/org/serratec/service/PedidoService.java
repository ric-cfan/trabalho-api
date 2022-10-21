package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Endereco;
import org.serratec.domain.ItemPedido;
import org.serratec.domain.Pedido;
import org.serratec.domain.Produto;
import org.serratec.dto.CategoriaDTO;
import org.serratec.dto.ClienteDTO2;
import org.serratec.dto.EnderecoDTO;
import org.serratec.dto.ItemPedidoDTO;
import org.serratec.dto.PedidoDTO;
import org.serratec.dto.ProdutoDTO;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.serratec.repository.ItemPedidoRepository;
import org.serratec.repository.PedidoRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	
	public List<PedidoDTO> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		
		for (Pedido pedido : pedidos) {
			List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
			List<ItemPedidoDTO> itemPedidosDTO = new ArrayList<>();
			for (ItemPedido itemPedido : itemPedidos) {
				Produto produto = produtoRepository.getById(itemPedido.getProduto().getId());
				CategoriaDTO categoria = new CategoriaDTO(itemPedido.getProduto().getCategoria());
				ProdutoDTO produtoDTO = new ProdutoDTO(produto,categoria);
				itemPedidosDTO.add(new ItemPedidoDTO(itemPedido, produtoDTO));
			}
			EnderecoDTO endereco = new EnderecoDTO(pedido.getCliente().getEndereco());
			ClienteDTO2 cliente = new ClienteDTO2(pedido.getCliente(), endereco);
			pedidosDTO.add(new PedidoDTO(pedido,cliente,itemPedidosDTO));
			
		}
		return pedidosDTO;
	}
}







