package org.serratec.repository;

import org.serratec.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Produto getById(Long id);

	Produto findByDescricao(String descricao);
}
