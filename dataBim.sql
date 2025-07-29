CREATE DATABASE IF NOT EXISTS connector_db;
USE connector;

CREATE TABLE connector (
	id INT AUTO_INCREMENT PRIMARY KEY, 
	image_path VARCHAR(255) not null, 
	numero_conexoes INT, 
	argola BOOLEAN,
	cabo BOOLEAN,
	conduite BOOLEAN,
	cabo_conduite BOOLEAN,
	equipamento BOOLEAN,
	aereo  BOOLEAN,
	tamanho BOOLEAN
	
); 
