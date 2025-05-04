package msConglomerado.configuracion;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import msConglomerado.domain.persistenceport.msConglomeradoPersistencePort;
import msConglomerado.domain.serviceport.msConglomeradoServicePort;
import msConglomerado.domain.usecase.msConglomeradoUseCase;
import msConglomerado.jpa.postgresql.adapter.msConglomeradoPostgresqlAdapter;
import msConglomerado.jpa.postgresql.mappers.msConglomeradoMapper;
import msConglomerado.jpa.postgresql.repository.msConglomeradoRepository;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final msConglomeradoMapper vmsConglomeradoMapper;
    private final msConglomeradoRepository vmsConglomeradoRepository;

    @Bean
    public msConglomeradoServicePort vmsConglomeradoServicePort(){
        return new msConglomeradoUseCase(vmsConglomeradoPersistencePort());
    }
    @Bean
    public msConglomeradoPersistencePort vmsConglomeradoPersistencePort(){
        return new msConglomeradoPostgresqlAdapter(vmsConglomeradoRepository, vmsConglomeradoMapper);
    }
}
