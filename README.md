# Trabalho Final API Restful - Serratec 2022.2 (Grupo 4)

## Projeto de API desenvolvida para um ecommerce.


## Regras de Negócio:
● No ato de cadastro de um novo pedido deverá ser calculado os valores bruto e
líquido dos produtos: valor bruto (preço venda * qtde) e valor líquido (valor bruto –
valor desconto; sendo esse último calculado através da aplicação do percentual de
desconto sobre o valor bruto);
● Ao final do cadastro de um novo pedido deverá ser calculado e armazenado o seu
valor total: soma dos valores líquidos dos itens do pedido;
● Os dados do endereço do Cliente deverão ser obtidos a partir de sua coleta numa
API externa de consulta de CEP;
● Não deverá ser possível cadastrar um pedido com data retroativa à atual;
● Não deverá ser possível cadastrar um produto com descrição idêntica a uma já
existente;
● Não deverá ser possível cadastrar diferentes clientes com um mesmo número de
CPF;
● Não deverá ser possível cadastrar diferentes clientes com um mesmo endereço de
e-mail;

## Requisitos:
● O banco de dados e as tabelas deverão ser criados de acordo com o DER fornecido
abaixo, utilizando o flyway;
● Não será permitido alterar a estrutura/relacionamento entre as entidades constantes
no DER;
● Todos os endpoints disponibilizados pela API deverão estar funcionais e realizando
os processos para os quais foram desenvolvidos;
● Para todos os recursos da API deverão ser disponibilizados os métodos CRUD;
● Deverá ser criado um Relatório de Pedido, contendo: id do pedido, data do pedido,
valor total; Relação de itens do pedido: código e nome do produto, preço de venda,
quantidade, valor bruto, percentual de desconto e valor líquido. Tal relatório deverá
ser criado com a utilização de DTO;
● Para transição dos dados referentes aos métodos CRUD poderão ser utilizadas as
Entidades ou DTOs, à escolha dos Grupos;
● Poderão ser criados diferentes DTOs referentes a uma mesma Entidade, caso
necessário;
● Em todos os métodos CRUD deverão ser identificadas e tratadas as exceções de
item não encontrado, com a exibição de mensagem personalizada;
● As imagens dos Produtos deverão ser armazenadas no banco de dados (utilizar o
tipo de dados blob/bytea para tal);
● A cada novo pedido cadastrado deverá ser enviado um e-mail contendo o Relatório
de Pedido (descrito acima);
● Deverão ser implementados a autenticação e o controle de acesso à API (com o
módulo de segurança do Spring + JWT);
