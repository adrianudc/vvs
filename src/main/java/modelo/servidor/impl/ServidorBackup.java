package modelo.servidor.impl;

import com.google.common.collect.Lists;
import modelo.contenido.Contenido;
import org.apache.commons.collections4.CollectionUtils;
import util.servidor.ServidorUtil;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class ServidorBackup extends ServidorImpl {

    /**
     *
     */
    private ServidorBackup servidorBackup;

    /**
     *
     * @param nombre del servidor
     * @param puerto en el que va a correr el servidor
     * @param servidorBackup , servidor que servira de backup
     */
    public ServidorBackup(final String nombre, final int puerto, final ServidorBackup servidorBackup) {
        super(nombre, puerto);
        this.servidorBackup = servidorBackup;
    }

    @Override
    public List<Contenido> buscar(final String subcadena, final String token) {
        List<Contenido> contenidos = super.buscar(subcadena, token);
        Collection<Contenido> result = CollectionUtils.subtract(contenidos, Lists.newArrayList(ServidorUtil
                .obtenerPublicidad()));
        if (result.isEmpty() && servidorBackup != null) {
            return servidorBackup.buscar(subcadena, token);
        }
        return contenidos;
    }
}
