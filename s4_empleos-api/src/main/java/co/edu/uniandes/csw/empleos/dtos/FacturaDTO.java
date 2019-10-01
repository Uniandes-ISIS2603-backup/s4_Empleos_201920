/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.dtos;
import co.edu.uniandes.csw.empleos.entities.FacturaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Estudiante
 */
public class FacturaDTO implements Serializable{
    
    private Long id;
    private Date fecha;
    private Integer valor;

    public FacturaDTO()
    {
        //Constructor Vacio
    }
    
          /**
     * Constructor a partir de la entidad
     *
     * @param facturaEntity La entidad del libro
     */
    public FacturaDTO(FacturaEntity facturaEntity) {
        
        if (facturaEntity != null) {
            this.id = facturaEntity.getId();
            this.fecha = facturaEntity.getFecha();
            this.valor = facturaEntity.getValor();
       }
    }
    

    
        /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del libro asociado.
     */
    public FacturaEntity toEntity() {
              
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId(this.id);
        facturaEntity.setValor(this.getValor());
        facturaEntity.setFecha(this.getFecha());
        
        
        return facturaEntity;
    }
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the valor
     */
    public Integer getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
