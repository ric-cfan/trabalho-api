package org.serratec.config;

import org.serratec.dto.ItemPedidoDTO3;
import org.serratec.dto.RelatorioPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	@Autowired
	JavaMailSender javaMailSender;
		
	public void sendMailPedido (RelatorioPedidoDTO relatorioPedidoDTO) {
		StringBuffer sb = new StringBuffer();
		sb.append("Prezado(a) "+ relatorioPedidoDTO.getNomeCliente()+", \n");
		sb.append("Pedido nº "+relatorioPedidoDTO.getIdPedido()+ "foi cadastrado\n");
		sb.append("Data: " + relatorioPedidoDTO.getDataPedido());
		sb.append("Itens do pedido: \n");
		
		for (ItemPedidoDTO3 item : relatorioPedidoDTO.getItens()) {
			sb.append("Item nº: " + item.getIdItemPedido()
			+"\nProduto: "+ item.getNomeProduto()
			+"\nQuantidade: R$ "+ item.getPrecoVenda()
			+"\nPreço de venda: R$ "+ item.getPrecoVenda()
			+"\nValor Bruto: R$ "+ item.getPrecoVenda()
			+"\nDesconto: "+ item.getPrecoVenda() + "%"
			+"\nValor Líquido: R$ " + item.getValorLiquido()+"\n");
		}
		sb.append("Valor total: R$ "+relatorioPedidoDTO.getValorTotal());
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("projetoapi2022@gmail.com");
		message.setSubject("Pedido cadastrado com sucesso");
		message.setText(sb.toString());
		message.setTo(relatorioPedidoDTO.getEmailCliente());
		javaMailSender.send(message);
		
	}

}
