
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.empleos.test.persistence;

import co.edu.uniandes.csw.empleos.entities.CuentaBancariaEntity;
import co.edu.uniandes.csw.empleos.persistence.CuentaBancariaPersistance;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de persistencia de Cuentas
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class CuentaBancariaPersistanceTest {

    @Inject
    private CuentaBancariaPersistance cuentaBancariaPersistance;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CuentaBancariaEntity> data = new ArrayList<CuentaBancariaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CuentaBancariaEntity.class.getPackage())
                .addPackage(CuentaBancariaPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CuentaBancariaEntity entity = factory.manufacturePojo(CuentaBancariaEntity.class);
        CuentaBancariaEntity result = cuentaBancariaPersistance.create(entity);
        Assert.assertNotNull(result);

        CuentaBancariaEntity foundEntity = em.find(CuentaBancariaEntity.class, result.getId());
        Assert.assertEquals(entity.getNumeroCuenta(), foundEntity.getNumeroCuenta());
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CuentaBancariaEntity").executeUpdate();
       
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CuentaBancariaEntity entity = factory.manufacturePojo(CuentaBancariaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una cuenta bancaria.
     *
     */
    @Test
    public void createCuentaBancariaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CuentaBancariaEntity newEntity = factory.manufacturePojo(CuentaBancariaEntity.class);

        CuentaBancariaEntity result = cuentaBancariaPersistance.create(newEntity);

        Assert.assertNotNull(result);

        CuentaBancariaEntity entity = em.find(CuentaBancariaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNumeroCuenta(), entity.getNumeroCuenta());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());

    }

    /**
     * Prueba para consultar la lista de premios.
     */
    @Test
    public void getCuentaBancariasTest() {
        List<CuentaBancariaEntity> list = cuentaBancariaPersistance.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CuentaBancariaEntity ent : list) {
            boolean found = false;
            for (CuentaBancariaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Prize.
     */
    @Test
    public void getCuentaBancariaTest() {
        CuentaBancariaEntity entity = data.get(0);
        CuentaBancariaEntity newEntity = cuentaBancariaPersistance.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getNumeroCuenta(), newEntity.getNumeroCuenta());
    }

    /**
     * Prueba para eliminar un Prize.
     */
    @Test
    public void deleteCuentaBancariaTest() {
        CuentaBancariaEntity entity = data.get(0);
        cuentaBancariaPersistance.delete(entity.getId());
        CuentaBancariaEntity deleted = em.find(CuentaBancariaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Prize.
     */
    @Test
    public void updateCuentaBancariaTest() {
        CuentaBancariaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CuentaBancariaEntity newEntity = factory.manufacturePojo(CuentaBancariaEntity.class);

        newEntity.setId(entity.getId());

        cuentaBancariaPersistance.update(newEntity);

        CuentaBancariaEntity resp = em.find(CuentaBancariaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumeroCuenta(), resp.getNumeroCuenta());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());

    }

}
