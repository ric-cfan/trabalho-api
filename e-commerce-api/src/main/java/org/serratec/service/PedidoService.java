package org.serratec.service;

import java.util.List;

import org.serratec.domain.Pedido;
import org.serratec.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
}
