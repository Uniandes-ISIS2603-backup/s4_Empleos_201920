/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.resources;

import co.edu.uniandes.csw.empleos.dtos.FacturaDTO;
import co.edu.uniandes.csw.empleos.ejb.FacturaLogic;
import co.edu.uniandes.csw.empleos.entities.FacturaEntity;
import co.edu.uniandes.csw.empleos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Nicolas Munar
 */
@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource {
    private static final Logger LOOGER =Logger.getLogger(FacturaResource.class.getName());
    
    @Inject
    private FacturaLogic facturaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    @POST
    public FacturaDTO createFactura(FacturaDTO factura) throws BusinessLogicException    {
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(factura.toEntity()));
        return nuevaFacturaDTO;
    }
    
     /**
     * Busca la factura con el id asociado recibido en la URL y lo devuelve.
     *
     * @param facturaId Identificador de la factura que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FacturaDTO} - La factura buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @GET
    @Path("{facturasId: \\d+}")
    public FacturaDTO getFactura(@PathParam("facturasId") Long facturaId) {
        FacturaEntity facEntity = facturaLogic.getFactura(facturaId);
        if (facEntity == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturaId + " no existe.", 404);
        }
        FacturaDTO facDTO = new FacturaDTO(facEntity);
        return facDTO;
    }
    
    
      /**
     * Actualiza la calificacion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param factId Identificador de la Factura que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param factura {@link FacturaDTO} La Factura que se desea guardar.
     * @return JSON {@link FacturaDTO} - La Factura guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Factura a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la Factura.
     */
    @PUT
    @Path("{facturasId: \\d+}")
    public FacturaDTO updateFactura(@PathParam("facturasId") Long factId, FacturaDTO factura) throws BusinessLogicException {
        factura.setId(factId);
        if (facturaLogic.getFactura(factId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + factId + " no existe.", 404);
        }
        FacturaDTO detailDTO = new FacturaDTO(facturaLogic.updateFactura(factId, factura.toEntity()));
        return detailDTO;
    }
    
    /**
     * Borra La factura con el id asociado recibido en la URL.
     *
     * @param factId Identificador del La Factura que se desea borrar. Este debe ser
     * una cadena de dígitos
     * @throws co.edu.uniandes.csw.empleos.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra La Factura.
     */
    @DELETE
    @Path("{facturasId: \\d+}")
    public void deleteFactura (@PathParam("facturasId") Long factId) throws BusinessLogicException {
        FacturaEntity entity = facturaLogic.getFactura(factId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + factId + " no existe.", 404);
        }
        facturaLogic.deleteFactura(factId);
        }
    
}
