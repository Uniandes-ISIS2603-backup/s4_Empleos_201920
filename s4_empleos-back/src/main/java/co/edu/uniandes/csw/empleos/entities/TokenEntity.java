/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Nicolas Munar
 */
@Entity
public class TokenEntity  extends BaseEntity implements Serializable {
        
    private String token;
    private String tipo;
    
    public TokenEntity(){
        //Constructor vacio
    }
    
      /**
     * @return the correo
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the correo to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
          /**
     * @return the correo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the correo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}