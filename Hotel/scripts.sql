CREATE DATABASE IF NOT EXISTS hotel DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE hotel.users (id  INT NOT NULL AUTO_INCREMENT,
					name VARCHAR(300) NOT NULL,
					login CHAR(20) NOT NULL,
					password VARCHAR(16) NOT NULL,
					email VARCHAR(100),
					ban BOOL,
					access CHAR(100),
					PRIMARY KEY (id));
					
CREATE TABLE hotel.rooms (id  INT NOT NULL AUTO_INCREMENT,
					number INT NOT NULL,
					capacity INT NOT NULL,
					type CHAR(40) NOT NULL,
					smoke BOOL,
					available  BOOL,
					PRIMARY KEY (id));		

CREATE TABLE hotel.booking (order_id  INT NOT NULL AUTO_INCREMENT,
					room_id INT NOT NULL,
					user_id INT NOT NULL,
					customer_name VARCHAR(150) NOT NULL,
					date_from DATE NOT NULL,
					date_to DATE NOT NULL,
					PRIMARY KEY (order_id),
					FOREIGN KEY (room_id) REFERENCES hotel.rooms(id),
					FOREIGN KEY (user_id) REFERENCES hotel.users(id));					