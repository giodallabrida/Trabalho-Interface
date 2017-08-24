package trabalhointerface;

import trabalhointerface.persistencia.UsuarioDAO;
import trabalhointerface.telas.Inicio;

public class Vendas {

    /*
CREATE DATABASE FAT_TRUCK;

USE FAT_TRUCK;

CREATE TABLE ADMIN (
    USER_ADM VARCHAR(60) PRIMARY KEY,
    SENHA_ADM VARCHAR(60) NOT NULL,
    PRIMEIRA_EX INT
);

INSERT INTO ADMIN VALUES ("admin", "admin", 0);

CREATE TABLE PRODUTO (
    COD_PDTO INT AUTO_INCREMENT PRIMARY KEY,
    NOM_PDTO VARCHAR (60) NOT NULL,
    PRECO_PDTO BIGINT(6.2) NOT NULL,
    ICON_PDTO BLOB NOT NULL
);

SELECT PRIMEIRA_EX FROM ADMIN;

CREATE TABLE VENDA (
    COD_VENDA INT auto_increment KEY,
    DAT_VENDA DATETIME default current_timestamp() NOT NULL,
    TOTAL_VENDA BIGINT (6.2) NOT NULL
);
     */
    
    public static void main(String[] args) {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        System.out.println("");
    }

}
