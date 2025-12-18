package com.ponscio.Facade;
import java.util.List;
import com.ponscio.model.Empleado;
import com.ponscio.model.error.BussinesError;
import com.ponscio.model.error.CrediYaError;
import com.ponscio.repository.BufferWriter;
import com.ponscio.repository.EmpleadoDAO;


import java.util.Map;

public class MenuEmpleadoF {

    private EmpleadoDAO empleadoDAO;
    private BufferWriter writer;

    public MenuEmpleadoF(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO ;
       this.writer = new BufferWriter("registro/Empleados.txt");
    }

    public String registrar(Empleado empleado) throws CrediYaError {
        
        if (empleado.getRol() > empleadoDAO.getRoles().size() || empleado.getRol() < 0) {
            throw new CrediYaError("\nEl Rol ingresado no es valido", BussinesError.VALOR_FUERA_DE_RANGO);
        }
        
        if (empleadoDAO.validarEmpleado(empleado.getDocumentoNumero())) {
            throw new CrediYaError("\nEl Documento ingresado ya esta registrado", BussinesError.VALOR_REPETIDO_NUMERO);
        }

        if (empleado.getSalario() < 1) throw new CrediYaError("\nEl monto del salario debe ser mayor a 0", BussinesError.FORMATO_INVALIDO_NUMERO);

        if (empleadoDAO.setEmpleado(empleado)) {
            writer.escribir(empleado.mostrarInfo(empleadoDAO.getRoles()));
            return "\nEmpleado guardado.";
        }
        else throw new CrediYaError("\nHubo un problema al intentar completar el proceso.", BussinesError.ERROR_FALLO_PROCESO);

        
    } 

    public List<Empleado> consultarByNombre(String nombre) {
        return empleadoDAO.getEmpleadoByNombre(nombre);
    }

    public List<Empleado> consultarById(int id) {
        return empleadoDAO.getEmpleadoById(id);
    }

    public List<Empleado> consultarByDocumento(String documento) {
        return empleadoDAO.getEmpleadoByDocumento(documento);
    }

    public String mostrarResultados(List<Empleado> empleados) {
        String resultados = "";
        for (Empleado e: empleados) {
            resultados += e.mostrarInfo(empleadoDAO.getRoles());
        }
         return resultados;
    }

    public String getRoles() {
        String lista = "";
        Map<Integer,String> roles = empleadoDAO.getRoles();
        for (Map.Entry<Integer,String> r : roles.entrySet()) {
            lista += "\n" + r.getKey() + ") " + r.getValue();
        }
        return lista;
    }
}
