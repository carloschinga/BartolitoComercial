package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.ComerRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComerService {

    private final ComerRepository repository;

    public ComerService(ComerRepository repository) {
        this.repository = repository;
    }

    // METODO PARA AGREGAR DESEMPEÑO
    public String agregarDesempenioJson(String desobj, int usecod, String mesano, String estado, String tipo, int hecAct) {

        // Calcula la fecha actual en Java
        //Timestamp fechaAhora = new Timestamp(System.currentTimeMillis());

        // Llama al repository pasando fechas ya calculadas
        return repository.agregarDesempenioJson(desobj, usecod, mesano, estado, tipo, hecAct);
    }

    // METODO PARA MODIFICAR DESEMPEÑO
    public String modificarDesempenioJson(int cuotVtaId, String desobj, int usecod, String mesano, String estado,
                                          String tipo, int hecAct) {

        // Llama al repository para ejecutar el SP
        return repository.modificarDesempenioJson(cuotVtaId, desobj, usecod, mesano, estado, tipo, hecAct);
    }

    // METODO PARA OBTENER TODOS LOS DESEMPEÑOS Y PODER LISTARLOS
    public String obtenerDesempenioJson() {
        return repository.listarDesempenioJson();
    }

    public String seleccionar(int cuotVtaId) {
        return repository.seleccionarJson(cuotVtaId);
    }

    public String seleccionarMeta(int cuotVtaId) {
        return repository.seleccionarMetaJson(cuotVtaId);
    }

    public String eliminar(int cuotVtaId) {
        return repository.eliminarJson(cuotVtaId);
    }

    /****************************METODOS PARA LA META FARMACIA******************/

    public String getMetaVenta(int cuotVtaId) {
        return repository.listarMetaVentaFarmacia(cuotVtaId);
    }


    public String modificarMetaFarmacia(int cuotVtaId, int siscod, BigDecimal cantidad, BigDecimal porc_estra, int usecod) {
        return repository.modificarMetaFarmaciaJson(cuotVtaId, siscod, cantidad, porc_estra, usecod);
    }


    public String listarDashboard() {
        return repository.listarDashboardJson();
    }

    /*================================MÉTODOS PARA ROLES===================================*/

    public String listarRoles() {
        return repository.listarRolesJson();
    }

    public String listarSucursalesMonto(int cuotVtaId) {
        return repository.listarSucursalesMonto(cuotVtaId);
    }

    public String listarRolesporFarmacia(int CuotVtaMesId) {
        return repository.listarRolesporFarmacia(CuotVtaMesId);
    }


    public String modificarRoles(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            String xml = XML.toString(json, "root"); // solo contenido XML, NO <?xml ... ?>

            System.out.println("XML generado: " + xml);

            return repository.modificarRolesXml(xml);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"resultado\":\"error\",\"mensaje\":\"Error al procesar JSON\"}";
        }
    }

    public String obtenerVendedores() {
        return repository.listarVendedores();
    }


    public String obtenerVendedoresAsignados(Integer cuotVtaRolId) {
        return repository.listarVendedoresAsignados(cuotVtaRolId);
    }


    public String modificarVendedores(String jsonString) {
        try {
            // Convertimos JSON a XML
            JSONObject json = new JSONObject(jsonString);
            String xml = XML.toString(json, "root"); // "root" es la raíz del XML

            System.out.println("XML generado para SP: " + xml); // Para debug

            return repository.modificarVendedoresXml(xml);

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"resultado\":\"error\",\"mensaje\":\"Error al procesar JSON\"}";
        }
    }

    /*==================Codido para el Dashboard Vendedores===============*/

    public String obtenerFarmacias() {
        return repository.obtenerFarmacias();
    }

    public String obtenerVendedoresLocal(int siscod) {
        return repository.listarVendedoresLocalJson(siscod);
    }

    public String obtenerMetaVentaVendedor(int usecod) {
        return repository.obtenerMetaVentaVendedorJson(usecod);
    }

    public String obtenerVendedoresPorFarmacia(int siscod) {
        return repository.listarVendedoresPorFarmaciaJson(siscod);
    }

    /*==================================GESTIÓN DE UMBRALES========================================*/

    public String obtenerUmbrales() {
        return repository.listarUmbralesJson();
    }

    public String modificarUmbrales(int codumb, String nomumb, BigDecimal minpor, BigDecimal maxpor) {
        return repository.modificarUmbralesJson(codumb, nomumb, minpor, maxpor);
    }

    /*=========================== OBJETIVO COMERCIAL DE PRODUCTOS ==============================*/
    public List<Map<String, Object>> obtenerProductos() {
        return repository.obtenerProductos();
    }

    public String agregarMetaVentaProducto(String codpro, String tipo, BigDecimal unidades, BigDecimal monto, int cuotVtaId, int useId) {
        return repository.agregarMetaVentaProducto(codpro, tipo, unidades,monto, cuotVtaId, useId);
    }

    public String listarMetaVentaProductos(int cuotVtaId) {
        return repository.obtenerMetaVentaProductos(cuotVtaId);
    }

    public String eliminarMetaVentaProducto(String codpro, int cuotVtaId) {
        return repository.eliminarMetaVentaProducto(codpro, cuotVtaId);
    }

    public String agregarPermanente(String codpro, int cantpro, int cuotVtaId) {
        return repository.agregarProductoPermanente(codpro, cantpro, cuotVtaId);
    }

    public String eliminarPermanente(String codpro, int cuotVtaId) {
        return repository.eliminarProductoPermanente(codpro, cuotVtaId);
    }


    public String listarSucursalesProductosDetalle() {
        return repository.obtenerSucursalesProductosDetalle();
    }

    public String listarObjetivoProductosDetalle(int cuotVtaId, String codpro) {
        return repository.obtenerObjetivoProductosDetalle(cuotVtaId, codpro);
    }

    public String agregarObjetivoProductosDetalle(int cuotVtaId, String codpro, int sucurId) {
        return repository.agregarObjetivoProductosDetalle(cuotVtaId, codpro, sucurId);
    }

    public String eliminarObjetivoProductosDetalle(int cuotVtaId, String codpro, int sucurId) {
        return repository.eliminarObjetivoProductosDetalle(cuotVtaId, codpro, sucurId);
    }

    public String modificarUnidades(int cuotVtaId, String codpro, BigDecimal unidades, BigDecimal monto) {
        return repository.modificarUnidades(cuotVtaId, codpro, unidades, monto);
    }

    public List<Map<String, Object>> obtenerDashboardProductoporProducto(int siscod, int usecod) {
        return repository.obtenerDashboardProductoporProducto(siscod, usecod);
    }

    public List<Map<String, Object>> obtenerDashboardProductoporVendedor(int siscod) {
        return repository.obtenerDashboardProductoporVendedor(siscod);
    }

    public List<Map<String, Object>> obtenerDashboardProducto() {
        return repository.obtenerDashboardProducto();
    }

    public List<Map<String, Object>> obtenerVendedoresConMetaProducto(int siscod) {
        return repository.obtenerVendedoresConMetaProducto(siscod);
    }


    /*=========================== LISTADO DE CAJJAS CERRADAS ==============================*/

    public List<Map<String, Object>> obtenerCajasCerradas(Date fecha1, Date fecha2, int siscod, int usecod1) {
        return repository.obtenerCajasCerradas(fecha1, fecha2, siscod, usecod1);
    }

    public List<Map<String, Object>> obtenerUsuariosCajasCerradas(String fecha1, String fecha2, int siscod) {
        return repository.obtenerUsuariosCajasCerradas(fecha1, fecha2, siscod);
    }

    /*=========================== DASHBOARD RESUMEN ==============================*/

    public List<Map<String, Object>> obtenerdDashnoardResumen(int cuotVtaId) {
        return repository.obtenerdDashnoardResumen(cuotVtaId);
    }
}
