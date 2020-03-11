package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repoProfessor;

	public List<Professor> buscarTodosProfessores() {
		return repoProfessor.findAll();
	}

	public Professor salvar(Professor professor) {
		return repoProfessor.save(professor);
	}
	
	public Professor buscarPorId(Integer id) throws ObjectNotFoundException {
		java.util.Optional<Professor> professor = repoProfessor.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado. Id" + id));
		
	}
	
	public Professor salvarAlteracao(Professor professorAlterado) throws ObjectNotFoundException {
		Professor professor = buscarPorId(professorAlterado.getId());
		professor.setId(professorAlterado.getId());
		professor.setNome(professorAlterado.getNome());
		return salvar(professor);
	}
	
	public void excluir(Integer id) {
		repoProfessor.deleteById(id);
	}

}
