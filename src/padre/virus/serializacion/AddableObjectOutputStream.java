package padre.virus.serializacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AddableObjectOutputStream extends ObjectOutputStream {
    /** Constructor que recibe OutputStream */
    public AddableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    /** Constructor sin parametros */
    protected AddableObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    /** Redefinicion para que no escriba ninguna cabecera en el flujo de salida,
     * por lo cual evita que se sobreescriba en la cabecera existente en un archivo
     * y asi permite agregar objetos al final del archivo sin perder los datos anteriores*/
    protected void writeStreamHeader() throws IOException {
    }

}