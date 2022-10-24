package org.serratec.repository;

import java.util.Optional;

import org.serratec.domain.Imagem;
import org.serratec.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
	public Optional<Imagem> findById(Long Id);
	public Optional<Imagem> findByProduto(Produto produto);
	
}
