
insert into Usuario (nome_completo, email,senha) VALUES
	('Teresinha Carolina Duarte','teresa@gmail.com','$2a$12$ZjvwXBjNeJTKazlrocGlZ.9l8dt9xFXOCoguie9.sfWa8xb.yyxy6'), --senha= 123456
	('Renan Bento Almada','renan@hotmail.com','$2a$12$WUzzKnhoHn.veC2K0EYRfO0dI3FRcBnGxQYvnEZ3VmIYqK9hiTbEK'), --senha = renan
	('Isabelly Tatiane Rocha','isabelly@gmail.com','$2a$12$Pso84rdA797enOwcB0StROGtnnBdeQ9792jn3nZKe5X77Y4JC/zaW'),	--senha = isabelly
	('Fabiana Luana Bárbara Monteiro','fabiana@gmail.com','$2a$12$J7RDWxkA0XjEzTUe11Xjde01KP9kZ6urFmelcI.JNWOP48RH8P9ke'); --senha = fabiana
	
	
INSERT INTO Perfil(tipo) VALUES 
	('ADMIN'),
	('USER');
	
INSERT INTO usuario_perfil(id_usuario,id_perfil,data_criacao) VALUES 
	(1,1,'2022-10-24'),
	(2,2,'2022-10-24'),
	(3,2,'2022-10-24'),
	(4,1,'2022-10-24');