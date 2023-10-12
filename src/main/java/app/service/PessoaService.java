package app.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.PessoaDTO;
import app.entity.Pessoa;
import app.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<PessoaDTO> listAll(){
		List<Pessoa> lista = pessoaRepository.findAll();
		List<PessoaDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++)
			listaDTO.add(this.toPessoaDTO(lista.get(i)));

		return listaDTO;
	}

	public PessoaDTO save(PessoaDTO pessoaDTO){
		Pessoa pessoa = this.toPessoa(pessoaDTO);

		Pessoa pessoasalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoasalva);
	}

	public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));

		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());

		Pessoa pessoaAtualizada = pessoaRepository.save(pessoa);
		return toPessoaDTO(pessoaAtualizada);
	}

	public void delete(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));

		pessoaRepository.delete(pessoa);
	}


	private PessoaDTO toPessoaDTO(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(pessoa.getId());
		pessoaDTO.setNome(pessoa.getNome());
		pessoaDTO.setIdade(pessoa.getIdade());
		return pessoaDTO;
	}

	private Pessoa toPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaDTO.getId());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());
		return pessoa;
	}

}