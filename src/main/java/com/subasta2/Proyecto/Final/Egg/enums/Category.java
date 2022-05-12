
package com.subasta2.Proyecto.Final.Egg.enums;

public enum Category {
    MUEBLE(1, "Mueble"),AUTOMOVIL(2, "Automovil"),MOTOCICLETA(3, "Motocicleta"),INMUEBLE(4, "Inmueble"),TECNOLOGIA(5, "Tecnologia"),ARTE(6,"Arte"),DECORACION(6, "Decoracion"), PERROS (7, "Perros");
    
    private Integer codigo;
    private String valor;

    private Category(Integer codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getValor() {
        return valor;
    }

}
