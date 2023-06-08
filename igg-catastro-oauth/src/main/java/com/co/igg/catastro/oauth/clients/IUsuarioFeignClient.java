package com.co.igg.catastro.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.co.igg.catastro.common.models.Usuario;

@FeignClient(name="servicio-usuarios-app")
public interface IUsuarioFeignClient {
	
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam String dsUsername);
	
	@PutMapping("/usuarios/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
	
}
