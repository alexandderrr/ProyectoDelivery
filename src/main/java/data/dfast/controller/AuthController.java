/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.dfast.controller;

/**
 *
 * @author Brandon
 */
import data.dfast.dto.LoginRequestDTO;
import data.dfast.model.entity.Usuario;
import data.dfast.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getCorreo());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok("Login exitoso. Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")");
            }
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}