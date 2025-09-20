package com.bartolito.comercial.service;

import com.bartolito.comercial.repository.ComerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String seleccionar(int cuotVtaId){
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

    public String listarRoles(){
        return repository.listarRolesJson();
    }

    public String listarSucursalesMonto(int cuotVtaId){
        return repository.listarSucursalesMonto(cuotVtaId);
    }

    public String listarRolesporFarmacia(int CuotVtaMesId){
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
        return repository.listarFarmaciasJson();
    }

    public String obtenerVendedoresLocal(int siscod) {
        return repository.listarVendedoresLocalJson(siscod);
    }

    public String obtenerMetaVentaVendedor(int usecod) {
        return repository.obtenerMetaVentaVendedorJson(usecod);
    }

    public String obtenerVendedoresPorFarmacia(int siscod){
        return repository.listarVendedoresPorFarmaciaJson(siscod);
    }

    /*==================================GESTIÓN DE UMBRALES========================================*/

    public String obtenerUmbrales(){
        return repository.listarUmbralesJson();
    }

    public String modificarUmbrales(int codumb, String nomumb, BigDecimal minpor, BigDecimal maxpor){
        return repository.modificarUmbralesJson(codumb, nomumb, minpor, maxpor);
    }
}
