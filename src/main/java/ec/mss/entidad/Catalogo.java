/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad;

/**
 *
 * @author andy
 */
public class Catalogo {
    
    private String grupo;
    private String catalogo;
    private Long codigo;
    private String nombre;
    private String tipoProducto;

    public Catalogo() {
    }
    
    public Catalogo(String grupo, String catalogo, Long codigo, String nombre, String tipoProducto) {
        this.grupo = grupo;
        this.catalogo = catalogo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
    }
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }
    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "grupo=" + grupo + ", catalogo=" + catalogo + ", codigo=" + codigo + ", nombre=" + nombre + ", tipoProducto=" + tipoProducto + '}';
    }
       
}
