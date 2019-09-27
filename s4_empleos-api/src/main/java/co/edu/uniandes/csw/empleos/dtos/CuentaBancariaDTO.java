/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.dtos;

import co.edu.uniandes.csw.empleos.entities.CuentaBancariaEntity;
import java.io.Serializable;

/**
 *
 * @author je.hernandezr
 */
public class CuentaBancariaDTO implements Serializable {

    private Long id;
    
    private String numeroCuenta;

    private String nombreBanco;

    private int tipoCuenta;

   //private EstudianteDTO estudiante;
    public CuentaBancariaDTO() {
    }

    public CuentaBancariaDTO(CuentaBancariaEntity cuentaBancariaEntity) {
        if (cuentaBancariaEntity != null) {
            this.id=cuentaBancariaEntity.getId();
            this.nombreBanco = cuentaBancariaEntity.getNombreBanco();
            this.numeroCuenta = cuentaBancariaEntity.getNumeroCuenta();
            setTipoCuenta(cuentaBancariaEntity.getTipoCuenta() == 2 ? "ahorros" : cuentaBancariaEntity.getTipoCuenta() == 3 ? "corriente" : "fff");
        }
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

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the nombreBanco
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * @param nombreBanco the nombreBanco to set
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * @return the tipoCuenta
     */
    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String pTipoCuenta) {

        if (pTipoCuenta.equalsIgnoreCase("Ahorros")) {
            tipoCuenta = 2;
        } else if (pTipoCuenta.equalsIgnoreCase("Corriente")) {
            tipoCuenta = 3;
        } else {
            tipoCuenta = 0;
        }
    }
    
     public CuentaBancariaEntity toEntity() {
        CuentaBancariaEntity cuentaBancaria = new CuentaBancariaEntity();
        cuentaBancaria.setId(this.getId());
        cuentaBancaria.setNombreBanco(this.getNombreBanco());
        cuentaBancaria.setNumeroCuenta(this.getNumeroCuenta());
        cuentaBancaria.setTipoCuenta(this.getTipoCuenta() == 2 ? "ahorros" : this.getTipoCuenta() == 3 ? "corriente" : "fff");
        
       // if(this.estudiante!=null)
       // {
          //  cuentaBancaria.setEstudiante(this.estudiante.toEntity());
        //}
        
        return cuentaBancaria;
    //}
     }
}