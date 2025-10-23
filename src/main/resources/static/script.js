const API_URL = 'http://localhost:8080/api/vehiculos';

let modoEdicion = false;
let placaOriginal = '';

const form = document.getElementById('vehiculoForm');
const btnAgregar = document.getElementById('btnAgregar');
const btnActualizar = document.getElementById('btnActualizar');
const btnCancelar = document.getElementById('btnCancelar');
const mensajeArea = document.getElementById('mensajeArea');
const listaVehiculos = document.getElementById('listaVehiculos');
const totalVehiculos = document.getElementById('totalVehiculos');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    if (modoEdicion) {
        actualizarVehiculo();
    } else {
        agregarVehiculo();
    }
});

btnCancelar.addEventListener('click', () => {
    cancelarEdicion();
});

btnActualizar.addEventListener('click', () => {
    actualizarVehiculo();
});

function agregarVehiculo() {
    const vehiculo = {
        placa: document.getElementById('placa').value.trim(),
        modelo: document.getElementById('modelo').value.trim(),
        color: document.getElementById('color').value.trim(),
        puertas: parseInt(document.getElementById('puertas').value)
    };

    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(vehiculo)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            mostrarMensaje(data.mensaje, 'success');
            form.reset();
            actualizarListaVehiculos(data.vehiculos);
        } else {
            mostrarMensaje(data.mensaje, 'error');
            actualizarListaVehiculos(data.vehiculos);
        }
    })
    .catch(error => {
        mostrarMensaje('Error al conectar con el servidor: ' + error, 'error');
    });
}

function actualizarVehiculo() {
    const vehiculo = {
        placa: placaOriginal,
        modelo: document.getElementById('modelo').value.trim(),
        color: document.getElementById('color').value.trim(),
        puertas: parseInt(document.getElementById('puertas').value)
    };

    fetch(`${API_URL}/${placaOriginal}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(vehiculo)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            mostrarMensaje(data.mensaje, 'success');
            cancelarEdicion();
            actualizarListaVehiculos(data.vehiculos);
        } else {
            mostrarMensaje(data.mensaje, 'error');
            actualizarListaVehiculos(data.vehiculos);
        }
    })
    .catch(error => {
        mostrarMensaje('Error al conectar con el servidor: ' + error, 'error');
    });
}

function editarVehiculo(vehiculo) {
    modoEdicion = true;
    placaOriginal = vehiculo.placa;
    
    document.getElementById('placa').value = vehiculo.placa;
    document.getElementById('modelo').value = vehiculo.modelo;
    document.getElementById('color').value = vehiculo.color;
    document.getElementById('puertas').value = vehiculo.puertas;
    
    document.getElementById('placa').disabled = true;
    
    btnAgregar.style.display = 'none';
    btnActualizar.style.display = 'block';
    btnCancelar.style.display = 'block';
    
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function cancelarEdicion() {
    modoEdicion = false;
    placaOriginal = '';
    
    form.reset();
    document.getElementById('placa').disabled = false;
    
    btnAgregar.style.display = 'block';
    btnActualizar.style.display = 'none';
    btnCancelar.style.display = 'none';
    
    mensajeArea.style.display = 'none';
}

function actualizarListaVehiculos(vehiculos) {
    if (!vehiculos || vehiculos.length === 0) {
        listaVehiculos.innerHTML = '<p class="no-data">No hay vehículos registrados</p>';
        totalVehiculos.textContent = '0';
        return;
    }
    
    totalVehiculos.textContent = vehiculos.length;
    
    listaVehiculos.innerHTML = '';
    
    vehiculos.forEach(vehiculo => {
        const vehiculoDiv = document.createElement('div');
        vehiculoDiv.className = 'vehiculo-item';
        vehiculoDiv.innerHTML = `
            <h3>Placa: ${vehiculo.placa}</h3>
            <p><strong>Modelo:</strong> ${vehiculo.modelo}</p>
            <p><strong>Color:</strong> ${vehiculo.color}</p>
            <p><strong>Puertas:</strong> ${vehiculo.puertas}</p>
            <div class="vehiculo-actions">
                <button class="btn-editar" onclick='editarVehiculo(${JSON.stringify(vehiculo)})'>
                    Editar
                </button>
            </div>
        `;
        listaVehiculos.appendChild(vehiculoDiv);
    });
}

function mostrarMensaje(mensaje, tipo) {
    mensajeArea.textContent = mensaje;
    mensajeArea.className = `mensaje ${tipo}`;
    mensajeArea.style.display = 'block';
    
    setTimeout(() => {
        mensajeArea.style.display = 'none';
    }, 5000);
}