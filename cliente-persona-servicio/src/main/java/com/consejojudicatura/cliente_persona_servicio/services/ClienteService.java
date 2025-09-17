package com.consejojudicatura.cliente_persona_servicio.services;

import com.consejojudicatura.cliente_persona_servicio.entity.Cliente;
import com.consejojudicatura.cliente_persona_servicio.entity.Persona;
import com.consejojudicatura.cliente_persona_servicio.repository.ClienteRepository;
import com.consejojudicatura.cliente_persona_servicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClientePublisher clientePublisher;

    //Lista de clientes
    public List<Cliente> listaClientes(){
        return clienteRepository.findByEstado(true);
    }

    //Almacenar un cliente
    public ResponseEntity<Object> guardarCliente(Cliente cliente){
        Optional<Persona> personaExiste = personaRepository.findByIdentificacion(cliente.getPersona().getIdentificacion());
        HashMap<String, Object> respuesta = new HashMap<>();

        if(personaExiste.isPresent()){
            respuesta.put("error", true);
            respuesta.put("mensaje", "La identificación ya existe");
            return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
        }

        clienteRepository.save(cliente);
        respuesta.put("mensaje", "Registro almacenado");
        clientePublisher.enviarCliente(cliente);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);


    }

    //Actualizar un cliente
    public ResponseEntity<Object> actualizarCliente(Cliente cliente){
        HashMap<String, Object> respuesta = new HashMap<>();

        if (cliente.getClienteId() == null) {
            respuesta.put("error", true);
            respuesta.put("mensaje", "No se puede actualizar el registro");
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        Cliente clienteDB = clienteRepository.findById(cliente.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        Optional<Persona> personaExiste = personaRepository.findByIdentificacion(cliente.getPersona().getIdentificacion());
        if(personaExiste.isPresent() && !personaExiste.get().getPersonaId().equals(clienteDB.getPersona().getPersonaId())){
            respuesta.put("error", true);
            respuesta.put("mensaje", "La identificación ya existe");
            return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
        }

        clienteDB.getPersona().setNombre(cliente.getPersona().getNombre());
        clienteDB.getPersona().setGenero(cliente.getPersona().getGenero());
        clienteDB.getPersona().setEdad(cliente.getPersona().getEdad());
        clienteDB.getPersona().setIdentificacion(cliente.getPersona().getIdentificacion());
        clienteDB.getPersona().setDireccion(cliente.getPersona().getDireccion());
        clienteDB.getPersona().setTelefono(cliente.getPersona().getTelefono());

        clienteDB.setContrasena(cliente.getContrasena());
        clienteDB.setEstado(cliente.getEstado());

        clienteRepository.save(clienteDB);

        respuesta.put("mensaje", "Registro actualizado correctamente");
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //Eliminar un cliente
    public ResponseEntity<Object> eliminarCliente(UUID id){
        HashMap<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> cliente = clienteRepository.findByClienteIdAndEstado(id, true);

        if (cliente.isPresent()) {
            cliente.get().setEstado(false);
            clienteRepository.save(cliente.get());
            respuesta.put("mensaje", "Registro eliminado");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }

        respuesta.put("error", true);
        respuesta.put("mensaje", "No se puede eliminar el registro");
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }


}
