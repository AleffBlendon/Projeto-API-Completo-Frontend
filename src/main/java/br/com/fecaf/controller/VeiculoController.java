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

    // === LISTAR TODOS ===
    @GetMapping("/listarVeiculos")
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    // === CADASTRAR NOVO ===
    @PostMapping("/cadastrarVeiculo")
    public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.salvarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    // === DELETAR POR ID ===
    @DeleteMapping("/deletarVeiculo/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable int id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    // === FILTRAR VEÍCULOS ===
    @GetMapping("/filtrar")
    public List<Veiculo> filtrarVeiculos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Double preco,
            @RequestParam(required = false) String status) {

        return veiculoService.filtrarVeiculos(marca, modelo, ano, preco, status);
    }

    // === ATUALIZAR VEÍCULO ===
    @PutMapping("/atualizarVeiculo/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(
            @PathVariable int id,
            @RequestBody Veiculo veiculoAtualizado) {

        Veiculo atualizado = veiculoService.atualizarVeiculo(id, veiculoAtualizado);
        return ResponseEntity.ok(atualizado);
    }
}
