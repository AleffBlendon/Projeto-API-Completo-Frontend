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

    // === LISTAR VEÍCULOS ===
    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    // === SALVAR NOVO VEÍCULO ===
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // === DELETAR VEÍCULO ===
    public void deletarVeiculo(int id) {
        veiculoRepository.deleteById(id);
    }

    // === FILTRAR VEÍCULOS ===
    public List<Veiculo> filtrarVeiculos(String marca, String modelo, Integer ano, Double preco, String status) {
        return veiculoRepository.findByFiltros(marca, modelo, ano, preco, status);
    }

    // === ATUALIZAR VEÍCULO ===
    public Veiculo atualizarVeiculo(int id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (veiculoAtualizado.getPreco() != null && veiculoAtualizado.getPreco() > 0)
            veiculo.setPreco(veiculoAtualizado.getPreco());

        if (veiculoAtualizado.getQuilometragem() != null && veiculoAtualizado.getQuilometragem() > 0)
            veiculo.setQuilometragem(veiculoAtualizado.getQuilometragem());

        if (veiculoAtualizado.getStatus() != null && !veiculoAtualizado.getStatus().isBlank())
            veiculo.setStatus(veiculoAtualizado.getStatus());

        return veiculoRepository.save(veiculo);
    }
}
