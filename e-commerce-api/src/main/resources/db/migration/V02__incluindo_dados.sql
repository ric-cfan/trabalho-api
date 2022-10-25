
insert into Endereco (cep, rua, bairro, cidade, numero, complemento, uf) VALUES
	('69921380','Rua Raimundo Farias','Montanhês','Rio Branco','964','n/a','AC'),
	('77062151','Rua Diógenes Japiassu','Loteamento Lago Sul (Taquaralto)','Palmas','904','n/a','TO'),									
	('32180620','Rua Praia Formosa','São Mateus','Contagem','164','n/a','MG'),
	('72311211','Quadra QN 501 Conjunto 11','Samambaia Sul (Samambaia)','Brasília','345','n/a','DF'),
	('72880710','Quadra SQ 19 Quadra 10','Centro','Cidade Ocidental','250','n/a','GO'),
	('74934575','Avenida Epiacauba','Cardoso Continuação','Aparecida de Goiânia','137','n/a','GO'),
	('59114100','Rua Francisco Aprígio','Nossa Senhora da Apresentação','Natal','120','n/a','RN'),
	('68926004','Avenida Manoel Francisco Guedes','Novo Horizonte','Santana','182','n/a','AP'),
	('76803680','Rua Anízio Gorayeb','São João Bosco','Porto Velho','999','n/a','RO'),
	('68628310','Rua Afonso Pena','Promissão II','Paragominas','599','n/a','PA');
	
insert into Cliente (nome_completo, email, cpf, telefone, data_nascimento, id_endereco) VALUES
	('Teresinha Carolina Duarte','teresinhacarolinaduarte@mpcnet.com.br','11849392501','68991570506','1978-08-12',1),
	('Renan Bento Almada','renanbentoalmada@sinsesp.com.br','79064503702','63992784557','2000-10-27',2),
	('Isabelly Tatiane Rocha','isabelly-rocha77@gamal.com.br','71559946776','31981525777','1990-05-17',3),	
	('Fabiana Luana Bárbara Monteiro','fabiana_luana_monteiro@gasparalmeida.com','00142139408','61994153082','1974-09-17',4),
	('Severino Leonardo Gabriel Cavalcanti','severino.leonardo.cavalcanti@pichler.com.br','75684240909','61992872449','2003-05-10',5),
	('Patrícia Marcela Pereira','patricia_marcela_pereira@tec3.com.br','46103543657','62982776852','1970-04-26',6),
	('Geraldo Eduardo Cardoso','geraldo_eduardo_cardoso@br.nestle.com','76214110589','84995390862','1943-01-08',7),
	('Analu Maria Jesus','analu-jesus81@gripoantonin.com','95665174246','96999365848','1996-08-16',8),
	('Mariana Isabel Malu da Luz','marianaisabeldaluz@bfgadvogados.com','04398729950','69985124308','1981-07-24',9),
	('Aparecida Antonella Almeida','aparecida_antonella_almeida@marquesalcantra.comabdv.com.br','79842098710','91996045563','2004-06-20',10);
	
INSERT INTO Categoria (nome, descricao) VALUES
	('Informática', 'Computadores e demais itens de informática'),
	('Celulares', 'Celulares e demais aparelhos de comunicação');

INSERT INTO Produto (nome, descricao, qtd_estoque, data_cadastro, valor_unitario, id_categoria) VALUES
	('Suporte de notebook', 'Item para apoiar o notebook', 100, '2022-10-19', 50.0, 1),
	('Mouse sem fio', 'Um mouse sem fio', 200, '2022-10-20', 25.0, 1),
	('Smartphone', 'Um smartphone', 50, '2022-10-20', 800.0, 1);
	
INSERT INTO Pedido (data_pedido, data_entrega, data_envio, status, valor_total, id_cliente) VALUES
	('2022-10-20', '2022-10-30', '2022-10-19', 'T', 100.0, 1),
	('2022-10-20', '2022-10-30', '2022-10-20', 'T', 150.0, 1),
	('2022-10-20', '2022-10-30', '2022-10-20', 'T', 800.0, 2);

INSERT INTO Item_pedido (quantidade, preco_venda, percentual_desconto, valor_bruto, valor_liquido, id_produto, id_pedido) VALUES
	(2, 100.0, 0, 50.0, 50.0, 1, 1),
	(2, 100.0, 0, 50.0, 50.0, 1, 2),
	(2, 50.0, 0, 25.0, 25.0, 2, 2),
	(2, 800.0, 0, 800.0, 800.0, 3, 3);
	