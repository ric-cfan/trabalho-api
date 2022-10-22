package org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.Pedido;
import org.serratec.dto.PedidoDTO;
import org.serratec.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<PedidoDTO> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			pedidosDTO.add(new PedidoDTO(pedido));

		}
		return pedidosDTO;
	}
}
