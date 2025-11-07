const API_URL = "http://localhost:8080/api/veiculos";

// === LISTAR VEÍCULOS ===
async function listarVeiculos() {
  const response = await fetch(`${API_URL}/listarVeiculos`);
  const veiculos = await response.json();
  const container = document.getElementById("cardsContainer");
  container.innerHTML = "";

  veiculos.forEach(v => {
    const card = document.createElement("div");
    card.classList.add("card");

    card.innerHTML = `
      <img src="${v.foto || 'https://via.placeholder.com/300x200?text=Sem+Foto'}" alt="${v.modelo}">
      <div class="card-content">
        <h3>${v.marca || 'Sem marca'} ${v.modelo || ''}</h3>
        <p><b>Cor:</b> ${v.cor}</p>
        <p><b>Ano:</b> ${v.ano}</p>
        <p><b>Preço:</b> R$ ${v.preco.toFixed(2)}</p>
        <p><b>Status:</b> ${v.status}</p>
        <p><b>Quilometragem:</b> ${v.quilometragem} km</p>
      </div>
      <button onclick="deletarVeiculo(${v.id})">Excluir</button>
    `;
    container.appendChild(card);
  });
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

// === Carregar lista ao iniciar ===
listarVeiculos();
