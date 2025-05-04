package msConglomerado.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import msConglomerado.domain.persistenceport.msConglomeradoPersistencePort;
import msConglomerado.domain.serviceport.msConglomeradoServicePort;
import msConglomerado.dto.msConglomeradoDTO;

import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class msConglomeradoUseCase implements msConglomeradoServicePort {
    private final msConglomeradoPersistencePort vmsConglomeradoPersistencePort;


    @Override
    public void saveConglomerado(msConglomeradoDTO vmsConglomeradoDTO) {
        validarPrecision(vmsConglomeradoDTO.getPrecision());

        String coordenadasFormateadas = formatearCoordenadas(vmsConglomeradoDTO.getCoordenadas_centro());
        vmsConglomeradoDTO.setCoordenadas_centro(coordenadasFormateadas);


        vmsConglomeradoPersistencePort.saveConglomerado(vmsConglomeradoDTO);
    }

    @Override
    public void deleteConglomerado(String idConglomerado) {
        vmsConglomeradoPersistencePort.deleteConglomerado(idConglomerado);

    }

    @Override
    public boolean updateConglomerado(msConglomeradoDTO vmsConglomeradoDTO) {
        vmsConglomeradoPersistencePort.updateConglomerado(vmsConglomeradoDTO);
        return false;
    }

    @Override
    public List<msConglomeradoDTO> getAllConglomerado() {
        return vmsConglomeradoPersistencePort.getAllConglomerado();
    }

    @Override
    public msConglomeradoDTO getConglomerado(String idConglomerado) {
        return vmsConglomeradoPersistencePort.getConglomerado(idConglomerado);
    }

    private void validarPrecision(Double precision) {
        if (precision == null) {
            throw new IllegalArgumentException("La precisión no puede ser nula.");
        }
        if (precision > 3.0) {
            throw new IllegalArgumentException("¡Precisión supera los 3m! Debe ser menor o igual a 3m.");
        }
    }

    private String formatearCoordenadas(String coordenadas) {
        if (coordenadas == null || !coordenadas.contains(",")) {
            throw new IllegalArgumentException("Coordenadas inválidas.");
        }
        String[] partes = coordenadas.split(",");
        double latitud = Double.parseDouble(partes[0]);
        double longitud = Double.parseDouble(partes[1]);
        return convertirDecimalAGMS(latitud) + ", " + convertirDecimalAGMS(longitud);
    }

    private String convertirDecimalAGMS(double decimal) {
        int grados = (int) decimal;
        double minutosDecimal = Math.abs((decimal - grados) * 60);
        int minutos = (int) minutosDecimal;
        double segundos = (minutosDecimal - minutos) * 60;

        DecimalFormat df = new DecimalFormat("00.00");
        return Math.abs(grados) + "°" + minutos + "’" + df.format(segundos) + "’’";
    }



}
