/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.resources;

import co.edu.uniandes.csw.empleos.dtos.CalificacionDTO;
import co.edu.uniandes.csw.empleos.ejb.CalificacionEstudianteLogic;
import co.edu.uniandes.csw.empleos.ejb.CalificacionLogic;
import co.edu.uniandes.csw.empleos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 *
 * @author Nicolas Munar */
@Path("estudiantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
class EstudianteResource {
    

}
