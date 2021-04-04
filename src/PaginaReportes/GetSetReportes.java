package PaginaReportes;

import java.sql.Date;

public class GetSetReportes {

    private String folio;
    private String descripcion;
    private String observacion;
    private String tipoVale;
    private String entrego;
    private String recibio;
    private Date fechaDate;
    private String fechaString;
    private String codigoInsumoModificar;
    private double cantidad;
    private String unidad;
    private String nombreProducto;
    private int idVale;

    public GetSetReportes(int idVale, String folio, String descripcion, String observacion, String tipoVale, String entrego, String recibio, String fechaString, String codigoInsumoModificar, double cantidad, String unidad) {
        this.idVale = idVale;
        this.folio = folio;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.tipoVale = tipoVale;
        this.entrego = entrego;
        this.recibio = recibio;
        this.fechaString = fechaString;
        this.codigoInsumoModificar = codigoInsumoModificar;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public GetSetReportes(String folio, String descripcion, String observacion, String tipoVale, String entrego, String recibio, String fechaString, String codigoInsumoModificar, double cantidad, String unidad, String nombreProducto) {
        this.folio = folio;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.tipoVale = tipoVale;
        this.entrego = entrego;
        this.recibio = recibio;
        this.fechaString = fechaString;
        this.codigoInsumoModificar = codigoInsumoModificar;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.nombreProducto = nombreProducto;
    }

    public GetSetReportes() {
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getIdVale() {
        return idVale;
    }

    public void setIdVale(int idVale) {
        this.idVale = idVale;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoVale() {
        return tipoVale;
    }

    public void setTipoVale(String tipoVale) {
        this.tipoVale = tipoVale;
    }

    public String getEntrego() {
        return entrego;
    }

    public void setEntrego(String entrego) {
        this.entrego = entrego;
    }

    public String getRecibio() {
        return recibio;
    }

    public void setRecibio(String recibio) {
        this.recibio = recibio;
    }

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public String getCodigoInsumoModificar() {
        return codigoInsumoModificar;
    }

    public void setCodigoInsumoModificar(String codigoInsumoModificar) {
        this.codigoInsumoModificar = codigoInsumoModificar;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
