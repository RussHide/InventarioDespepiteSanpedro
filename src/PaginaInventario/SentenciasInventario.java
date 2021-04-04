package PaginaInventario;

import com.jfoenix.controls.JFXDatePicker;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class SentenciasInventario {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int min = 0;
    int max = 0;
    double entrada = 0;
    double salida = 0;
    double resultado = 0;
    static char[] decimal = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};
    static char[] entero = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    static char[] fecha = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '/'};

    public static ObservableList<GetSetInventario> GetDatosTabla() {
        java.sql.Connection conn = Conexion.ConexionBD.SubConexion();
        ObservableList<GetSetInventario> listaPaginaInventario = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from productos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaPaginaInventario.add(new GetSetInventario(rs.getInt("idProducto"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("cantidad"),
                        rs.getString("unidad")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaPaginaInventario;
    }

    public boolean AgregarProducto(GetSetInventario ObjetoInventario) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("insert into productos (codigo,nombre,descripcion,cantidad,unidad) values (?,?,?,?,?)");
            ps.setString(1, ObjetoInventario.getCodigo());
            ps.setString(2, ObjetoInventario.getNombre());
            ps.setString(3, ObjetoInventario.getDescripcion());
            ps.setDouble(4, ObjetoInventario.getCantidad());
            ps.setString(5, ObjetoInventario.getUnidad());
            ps.executeUpdate();
            datosTablaProductos();
        } catch (Exception e) {
            System.out.println("Error al agregar" + e);
            return false;
        }
        return true;
    }

    public boolean ModificarProducto(GetSetInventario ObjetoInventario) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("update productos set codigo = ?, nombre = ?, descripcion = ?, cantidad = ?, unidad = ? where idProducto = " + ModalModificarProductoController.lblControlId1.getText());
            ps.setString(1, ObjetoInventario.getCodigo());
            ps.setString(2, ObjetoInventario.getNombre());
            ps.setString(3, ObjetoInventario.getDescripcion());
            ps.setDouble(4, ObjetoInventario.getCantidad());
            ps.setString(5, ObjetoInventario.getUnidad());
            ps.executeUpdate();
            datosTablaProductos();
        } catch (Exception e) {
            System.out.println("Error al modificar" + e);
            return false;
        }
        return true;
    }

    public boolean EliminarProducto(GetSetInventario ObjetoInventario) {
        try {
            conn = Conexion.ConexionBD.SubConexion();
            ps = conn.prepareStatement("delete from productos where idProducto = " + ObjetoInventario.getId());
            ps.executeUpdate();
            datosTablaProductos();
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e);
            return false;
        }
        return true;
    }

    public void datosTablaProductos() {
        PaginaInventarioController.colCodigoBarrasProducto1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        PaginaInventarioController.colNombreProducto1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        PaginaInventarioController.colDescripcionProducto1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        PaginaInventarioController.colCantidadProducto1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        PaginaInventarioController.colUnidadProducto1.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        PaginaInventarioController.listaPaginaProductos = GetDatosTabla();
        PaginaInventarioController.tablaInventario1.setItems(PaginaInventarioController.listaPaginaProductos);
    }

    public ObservableList<GetSetInventario> buscarDatos() {
        conn = Conexion.ConexionBD.SubConexion();
        ObservableList<GetSetInventario> listaGenerarInventario = FXCollections.observableArrayList();
        try {
            ps = conn.prepareStatement("select MIN(idProducto) as MinValor from productos");
            rs = ps.executeQuery();
            if (rs.next()) {
                min = rs.getInt("MinValor");
            }

            ps = conn.prepareStatement("select MAX(idProducto) as MaxValor from productos");
            rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt("MaxValor");
            }

            do {
                ps = conn.prepareStatement("select sum(r.cantidad) as tablaEntradas from Vales as r INNER JOIN productos as p on r.CODIGOINSUMO = p.codigo where r.fechaDate between '2021-01-01' and '" + ModalExportarInventarioController.txtFechaReporte1.getValue() + "' and idProducto = " + min + " and r.tipoVale = 'Entrada' GROUP BY idProducto");
                rs = ps.executeQuery();
                if (rs.next()) {
                    entrada = rs.getDouble("tablaEntradas");
                } else {
                    entrada = 0;
                }

                ps = conn.prepareStatement("select sum(r.cantidad) as tablaSalidas from Vales as r INNER JOIN productos as p on r.CODIGOINSUMO = p.codigo where r.fechaDate between '2021-01-01' and '" + ModalExportarInventarioController.txtFechaReporte1.getValue() + "' and idProducto = " + min + " and r.tipoVale = 'Salida' GROUP BY idProducto");
                rs = ps.executeQuery();
                if (rs.next()) {
                    salida = rs.getDouble("tablaSalidas");
                } else {
                    salida = 0;
                }

                resultado = entrada - salida;

                ps = conn.prepareStatement("select * from productos where idProducto = " + min);
                rs = ps.executeQuery();

                if (rs.next()) {
                    listaGenerarInventario.add(new GetSetInventario(rs.getInt("idProducto"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            resultado,
                            rs.getString("unidad")));
                }
                min++;
            } while (min <= max);

        } catch (Exception e) {
            System.out.println("Lista generar inventario errror " + e);
        }
        return listaGenerarInventario;

    }

    public void datosTablaBuscarGenerar() {
        try {

            ModalExportarInventarioController.colCodigo1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            ModalExportarInventarioController.colNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ModalExportarInventarioController.colDescripcion1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            ModalExportarInventarioController.colCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            ModalExportarInventarioController.colUnidades1.setCellValueFactory(new PropertyValueFactory<>("unidad"));

            ModalExportarInventarioController.listaGenerarInventarioXD = buscarDatos();
            ModalExportarInventarioController.tablaReporteInventario1.setItems(ModalExportarInventarioController.listaGenerarInventarioXD);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void acomodarDatePicker(JFXDatePicker datep) {
        datep.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }
}
