package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.PessoaDTO;
import app.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity<?> handle() {
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@GetMapping
	private ResponseEntity<List<PessoaDTO>> listAll(){
		try {
			List<PessoaDTO> lista = pessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
		try {
			PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
		try {
			PessoaDTO pessoaAtualizada = pessoaService.update(id, pessoaDTO);
			return new ResponseEntity<>(pessoaAtualizada, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		try {
			pessoaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("erro")
	private ResponseEntity<List<PessoaDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
