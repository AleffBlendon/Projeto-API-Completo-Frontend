package br.com.fecaf.controller;

import br.com.fecaf.model.Veiculo;
import br.com.fecaf.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"}, allowedHeaders = "*")

public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // endpoint para listar veículos
    @GetMapping("/listarVeiculos")
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    // endpoint para cadastrar veículo
    @PostMapping("/cadastrarVeiculo")
    public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.salvarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    // endpoint para deletar veículo
    @DeleteMapping("/deletarVeiculo/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable int id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
