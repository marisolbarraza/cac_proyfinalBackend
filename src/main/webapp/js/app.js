document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('itemForm');
    const itemsTableBody = document.getElementById('itemsTableBody');
    
    // Función para cargar los elementos desde la API
    function loadItems() {
        fetch('http://localhost:8080/cac_backend/pelicula')
            .then(response => response.json())
            .then(data => {
                itemsTableBody.innerHTML = '';
                if (data) {
                    data.forEach(pelicula => {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${pelicula.id}</td>
                            <td>${pelicula.titulo}</td>
                            <td>${pelicula.genero}</td>
                            <td>${pelicula.duracion}</td>
                            <td>${pelicula.director}</td>
                            <td>${pelicula.reparto}</td>
                            <td>${pelicula.sinopsis}</td>
                            <td>
                                <button class="btn-eliminar" onclick="deleteItem(${pelicula.id})">Eliminar</button>
                            </td>
                            <td>
                                <button class="btn-editar" onclick="editItem(${pelicula.id}, '${pelicula.titulo}', '${pelicula.genero}', ${pelicula.duracion}, '${pelicula.director}', '${pelicula.reparto}', '${pelicula.sinopsis}')">Editar</button>
                            </td>
                        `;
                        itemsTableBody.appendChild(row);
                    });
                } else {
                    console.error('No se encontraron películas');
                }
            })
            .catch(error => console.error('Error:', error));
    }

    // Función para editar un registro
    window.editItem = function(id, titulo, genero, duracion, director, reparto, sinopsis,imagen) {
        document.getElementById('id').value = id;
        document.getElementById('titulo').value = titulo;
        document.getElementById('genero').value = genero;
        document.getElementById('duracion').value = duracion;
        document.getElementById('director').value = director;
        document.getElementById('reparto').value = reparto;
        document.getElementById('sinopsis').value = sinopsis;
        document.getElementById('imagen').value = imagen;
    }

    // Función para eliminar un registro
    window.deleteItem = function(id) {
        fetch(`http://localhost:8080/cac_backend/pelicula/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                loadItems(); // Recarga los elementos después de eliminar
            } else {
                console.error('Error al eliminar el registro:', response.statusText);
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // Función para añadir o actualizar un registro
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Previene el envío del formulario

        const id = document.getElementById('id').value;
        const titulo = document.getElementById('titulo').value;
        const genero = document.getElementById('genero').value;
        const duracion = document.getElementById('duracion').value;
        const director = document.getElementById('director').value;
        const reparto = document.getElementById('reparto').value;
        const sinopsis = document.getElementById('sinopsis').value;
        const imagen = document.getElementById('imagen').value;

        const data = { id,titulo, genero, duracion, director, reparto, sinopsis,imagen };

        if (id) {
            // Actualizar el registro si hay un id
            fetch(`http://localhost:8080/cac_backend/pelicula/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                if (response.ok) {
                    loadItems(); // Recarga los elementos después de actualizar
                    form.reset(); // Limpia el formulario
                } else {
                    console.error('Error al actualizar el registro:', response.statusText);
                }
            })
            .catch(error => console.error('Error:', error));
        } else {
            // Añadir el registro si no hay id
            fetch('http://localhost:8080/cac_backend/pelicula', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                if (response.ok) {
                    loadItems(); // Recarga los elementos después de añadir
                    form.reset(); // Limpia el formulario
                } else {
                    console.error('Error al añadir el registro:', response.statusText);
                }
            })
            .catch(error => console.error('Error:', error));
        }
    });

    // Cargar los elementos cuando se carga la página
    loadItems();
});
