CREATE TABLE categoria (
                           id INT AUTO_INCREMENT,
                           nombre VARCHAR(100) NOT NULL,
                           tipo VARCHAR(20) NOT NULL,

                           CONSTRAINT pk_categoria PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;