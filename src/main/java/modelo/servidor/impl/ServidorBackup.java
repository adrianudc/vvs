package modelo.servidor.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import modelo.contenido.Contenido;
import util.servidor.ServidorUtil;

public class ServidorBackup extends ServidorImpl {

    private ServidorBackup servidorBackup;

    public ServidorBackup(String nombre, int puerto, ServidorBackup servidorBackup) {
        super(nombre, puerto);
        this.servidorBackup = servidorBackup;
    }

    @Override
    public List<Contenido> buscar(String subcadena, String token) {
        List<Contenido> contenidos = super.buscar(subcadena, token);
        Collection<Contenido> result = CollectionUtils.subtract(contenidos, Lists.newArrayList(ServidorUtil
                .obtenerPublicidad()));
        if (result.isEmpty() && servidorBackup != null) {
            return servidorBackup.buscar(subcadena, token);
        }
        return contenidos;
    }
}
