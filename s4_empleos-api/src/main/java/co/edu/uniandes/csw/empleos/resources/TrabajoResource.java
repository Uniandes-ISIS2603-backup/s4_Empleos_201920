/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.resources;

import co.edu.uniandes.csw.empleos.dtos.TrabajoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author David Dominguez
 */
@Path("trabajos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrabajoResource {

    private static final Logger LOOGER = Logger.getLogger(TrabajoResource.class.getName());

    @POST
    public TrabajoDTO createTrabajo(TrabajoDTO trabajo) {
        return trabajo;
    }
}
