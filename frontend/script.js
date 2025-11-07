const API_URL = "http://localhost:8080/api/veiculos";

// === LISTAR VEÍCULOS ===
async function listarVeiculos(filtros = {}) {
  let url = `${API_URL}/listarVeiculos`;

  // Só adiciona filtros preenchidos
  const params = new URLSearchParams();
  for (const [chave, valor] of Object.entries(filtros)) {
    if (valor && valor !== "null" && valor.trim() !== "") {
      params.append(chave, valor);
    }
  }

  // Se tiver algum filtro válido, troca o endpoint
  if ([...params].length > 0) {
    url = `${API_URL}/filtrar?${params.toString()}`;
  }

  try {
    const response = await fetch(url);
    if (!response.ok) throw new Error("Erro ao buscar veículos");
    const veiculos = await response.json();

    const container = document.getElementById("cardsContainer");
    container.innerHTML = "";

    if (!Array.isArray(veiculos) || veiculos.length === 0) {
      container.innerHTML = "<p>Nenhum veículo encontrado.</p>";
      return;
    }

    veiculos.forEach(v => {
      const card = document.createElement("div");
      card.classList.add("card");

      // Cor do status
      const statusCor = v.status?.toLowerCase() === "disponivel" ? "green" : "red";

      card.innerHTML = `
        <img src="${v.foto || 'https://via.placeholder.com/300x200?text=Sem+Foto'}" alt="${v.modelo}">
        <div class="card-content">
          <h3>${v.marca || 'Sem marca'} ${v.modelo || ''}</h3>
          <p><b>Cor:</b> ${v.cor}</p>
          <p><b>Ano:</b> ${v.ano}</p>
          <p><b>Preço:</b> R$ ${v.preco?.toFixed(2) || '0.00'}</p>
          <p><b>Status:</b> <span style="color:${statusCor}; font-weight:bold;">${v.status}</span></p>
          <p><b>Quilometragem:</b> ${v.quilometragem} km</p>
        </div>
        <div class="card-actions">
          <button onclick="editarVeiculo(${v.id}, ${v.preco}, ${v.quilometragem}, '${v.status}')">Editar</button>
          <button onclick="deletarVeiculo(${v.id})">Excluir</button>
        </div>
      `;
      container.appendChild(card);
    });
  } catch (error) {
    console.error("Erro ao listar veículos:", error);
  }
}

// === CADASTRAR NOVO VEÍCULO ===
document.querySelector("#formVeiculo").addEventListener("submit", async (e) => {
  e.preventDefault();

  const veiculo = {
    marca: document.getElementById("marca").value,
    modelo: document.getElementById("modelo").value,
    cor: document.getElementById("cor").value,
    ano: parseInt(document.getElementById("ano").value),
    quilometragem: parseInt(document.getElementById("quilometragem").value),
    preco: parseFloat(document.getElementById("preco").value),
    status: document.getElementById("status").value,
    foto: document.getElementById("foto").value
  };

  await fetch(`${API_URL}/cadastrarVeiculo`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(veiculo)
  });

  e.target.reset();
  listarVeiculos();
});

// === DELETAR VEÍCULO ===
async function deletarVeiculo(id) {
  if (!confirm("Deseja realmente excluir este veículo?")) return;

  await fetch(`${API_URL}/deletarVeiculo/${id}`, { method: "DELETE" });
  listarVeiculos();
}

// === EDITAR VEÍCULO ===
async function editarVeiculo(id, precoAtual, kmAtual, statusAtual) {
  const novoPreco = prompt("Novo preço:", precoAtual);
  const novaKm = prompt("Nova quilometragem:", kmAtual);
  const novoStatus = prompt("Novo status (disponivel/vendido):", statusAtual);

  if (!novoPreco || !novaKm || !novoStatus) return;

  const veiculoAtualizado = {
    preco: parseFloat(novoPreco),
    quilometragem: parseInt(novaKm),
    status: novoStatus
  };

  await fetch(`${API_URL}/atualizarVeiculo/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(veiculoAtualizado)
  });

  listarVeiculos();
}

// === FILTRAR VEÍCULOS ===
document.getElementById("btnFiltrar").addEventListener("click", () => {
  const filtros = {
    marca: document.getElementById("filtroMarca").value,
    modelo: document.getElementById("filtroModelo").value,
    ano: document.getElementById("filtroAno").value,
    preco: document.getElementById("filtroPreco").value,
    status: document.getElementById("filtroStatus").value
  };

  listarVeiculos(filtros);
});

// === LIMPAR FILTROS ===
document.getElementById("btnLimpar").addEventListener("click", () => {
  document.getElementById("filtroMarca").value = "";
  document.getElementById("filtroModelo").value = "";
  document.getElementById("filtroAno").value = "";
  document.getElementById("filtroPreco").value = "";
  document.getElementById("filtroStatus").value = "";
  listarVeiculos();
});

// === Carregar lista ao iniciar ===
listarVeiculos();
