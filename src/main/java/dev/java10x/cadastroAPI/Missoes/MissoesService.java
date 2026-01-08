package dev.java10x.cadastroAPI.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;
    private final MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar missoes
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // listar missoes por ID
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    // Criar missoes
    public MissoesDTO criarMissoes(MissoesDTO missoesDTO){
        MissoesModel missoes = new MissoesMapper().map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    // Deletar missoes
    public void deletarMissaoPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    // Alterar nissoes por Id
    public MissoesDTO atualizarMissoes(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missoesExistente = missoesRepository.findById(id);
        if (missoesExistente.isPresent()) {
            MissoesModel missoesAtualizadas = missoesMapper.map(missoesDTO);
            missoesAtualizadas.setId(id);
            MissoesModel missoesSalvo = missoesRepository.save(missoesAtualizadas);
            return missoesMapper.map(missoesSalvo);
        }
        return null;
    }

}
