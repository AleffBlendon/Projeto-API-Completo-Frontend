package br.com.fecaf.services;

import br.com.fecaf.model.Veiculo;
import br.com.fecaf.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // método para listar veículos
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // método para salvar novo veículo
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // método para deletar veículo
    public void deletarVeiculo(int id) {
        veiculoRepository.deleteById(id);
    }
}
