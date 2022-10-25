create table Endereco(
	id_endereco serial primary key,
	cep varchar(8) not null,
	rua varchar(80) not null,
	bairro varchar(50) not null,
	cidade varchar(80) not null,
	numero varchar(20)not null,
	complemento varchar(80),
	uf varchar(2) not NULL
);
																					
create table Cliente (
	id_cliente serial primary key,
	nome_completo varchar(50) not null,
	email varchar(80) not null unique,
	cpf varchar(11) not null unique,
	telefone varchar(40) not null,
	data_nascimento date,
	id_endereco int NOT NULL REFERENCES Endereco(id_endereco)
);																			

create table Categoria(
	id_categoria serial primary key,
	nome varchar(30) not null unique,
	descricao varchar(200)
);

create table Produto(
	id_produto serial primary key,
	nome varchar(30) not null unique,
	descricao varchar(200),
	qtd_estoque int,
	data_cadastro date,
	valor_unitario real not null,
	id_categoria int NOT NULL REFERENCES Categoria(id_categoria)
);

CREATE TABLE Imagem(
	id_imagem serial PRIMARY KEY,
	dados BYTEA,
	tipo varchar(50),
	id_produto int NOT NULL REFERENCES Produto(id_produto)
);


create table Pedido(
	id_pedido serial primary key,
	data_pedido date not null,
	data_entrega date,
	data_envio date,
	status varchar(1) not null,
	valor_total real not null,
	id_cliente int NOT NULL REFERENCES Cliente(id_cliente)
);


create table Item_Pedido(
	id_item_pedido serial primary key,
	quantidade int not null,
	preco_venda real not null,
	percentual_desconto real not null,
	valor_bruto real not null,
	valor_liquido real not null,
	id_produto int NOT NULL REFERENCES Produto(id_produto),
	id_pedido int NOT NULL REFERENCES Pedido(id_pedido)
);




