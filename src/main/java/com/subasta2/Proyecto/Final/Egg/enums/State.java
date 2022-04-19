
package com.subasta2.Proyecto.Final.Egg.enums;

public enum State {
    NUEVO(1, "Nuevo"),DESGASTADO(2, "Desgastado"),USADO(3, "Usado"),DEPLORABLE(4, "Deplorable"),ROTO(5, "Roto");
    
    private Integer codigo;
    private String valor;

    private State(Integer codigo, String valor) {
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
