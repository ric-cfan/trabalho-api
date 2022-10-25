package org.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.config.MailConfig;
import org.serratec.domain.Cliente;
import org.serratec.domain.ItemPedido;
import org.serratec.domain.Pedido;
import org.serratec.domain.Produto;
import org.serratec.dto.ItemPedidoDTO2;
import org.serratec.dto.PedidoDTO;
import org.serratec.dto.PedidoDTO2;
import org.serratec.dto.RelatorioPedidoDTO;
import org.serratec.exception.DataPedidoAnteriorException;
import org.serratec.exception.EmailException;
import org.serratec.exception.NotFoundException;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.PedidoRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private MailConfig mailConfig;

	public List<PedidoDTO> listarTodos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			pedidosDTO.add(new PedidoDTO(pedido));

		}
		return pedidosDTO;
	}

	public PedidoDTO findById(Long idPedido) {
		if (!pedidoRepository.findById(idPedido).isPresent()) {
			throw new NotFoundException("Pedido não encontrado!");
		}
		PedidoDTO pedidoDTO = new PedidoDTO(pedidoRepository.findById(idPedido).get());
		return pedidoDTO;
		
	}

	public PedidoDTO cadastrar(PedidoDTO2 pedido) throws DataPedidoAnteriorException {
		if (pedido.getDataPedido().isBefore(LocalDate.now())) {
			throw new DataPedidoAnteriorException();
		}

		Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).get();

		List<ItemPedido> listaItemPedido = new ArrayList<>();
		List<ItemPedidoDTO2> listaItemPedidoDTO = new ArrayList<ItemPedidoDTO2>(pedido.getItens());
		Double valorTotal = 0.;

		for (ItemPedidoDTO2 itemPedidoDTO2 : listaItemPedidoDTO) {

			Produto produto = produtoRepository.findById(itemPedidoDTO2.getIdProduto()).get();
			listaItemPedido.add(new ItemPedido(itemPedidoDTO2, produto));

			valorTotal += produto.getValorUnitario() * (1 - itemPedidoDTO2.getPercentualDesconto() / 100)
					* itemPedidoDTO2.getQuantidade();
		}

		Pedido pedidoBanco = new Pedido(pedido, valorTotal, cliente, listaItemPedido);
		Pedido pedidoSalvo = pedidoRepository.save(pedidoBanco);

		PedidoDTO pedidoDTO = new PedidoDTO(pedidoSalvo);
    	RelatorioPedidoDTO relatorioPedidoDTO = new RelatorioPedidoDTO(pedidoSalvo);

		mailConfig.sendMailPedido(relatorioPedidoDTO);
    	try {
    		mailConfig.sendMailPedido(relatorioPedidoDTO);
		} catch (Exception e) {
			throw new EmailException("Houve um erro no envio do email, favor verificar se o email está correto.");
		}
        return pedidoDTO;
    }

	public PedidoDTO atualizar(PedidoDTO2 pedido, Long idPedido) {

		if (!pedidoRepository.existsById(idPedido)) {
			throw new NotFoundException("Pedido não encontrado!");
		}
			Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).get();

			List<ItemPedido> listaItemPedido = new ArrayList<>();
			List<ItemPedidoDTO2> listaItemPedidoDTO = new ArrayList<ItemPedidoDTO2>(pedido.getItens());
			Double valorTotal = 0.;

			for (ItemPedidoDTO2 itemPedidoDTO2 : listaItemPedidoDTO) {

				Produto produto = produtoRepository.findById(itemPedidoDTO2.getIdProduto()).get();
				listaItemPedido.add(new ItemPedido(itemPedidoDTO2, produto));

				valorTotal += produto.getValorUnitario() * (1 - itemPedidoDTO2.getPercentualDesconto() / 100)
						* itemPedidoDTO2.getQuantidade();
			}

			Pedido pedidoBanco = new Pedido(pedido, valorTotal, cliente, listaItemPedido);
			pedidoBanco.setId(idPedido);
			Pedido pedidoSalvo = pedidoRepository.save(pedidoBanco);

			PedidoDTO pedidoDTO = new PedidoDTO(pedidoSalvo);

			return pedidoDTO;
		}

	public void deleteById(Long idPedido) {
		if(!pedidoRepository.existsById(idPedido)) {
			throw new NotFoundException("Pedido não encontrado!");
		}
		pedidoRepository.deleteById(idPedido);
	}
}
