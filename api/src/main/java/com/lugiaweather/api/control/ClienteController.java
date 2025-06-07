package com.lugiaweather.api.control;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import com.lugiaweather.api.dto.UserDTO;
import com.lugiaweather.api.dto.ViaCepDTO;
import com.lugiaweather.api.model.Endereco;
import com.lugiaweather.api.model.Telefone;
import com.lugiaweather.api.model.User;
import com.lugiaweather.api.repository.EnderecoRepository;
import com.lugiaweather.api.repository.TelefoneRepository;
import com.lugiaweather.api.repository.UserRepository;
import com.lugiaweather.api.security.SecurityConfig;
import com.lugiaweather.api.service.ClienteCashingService;
import com.lugiaweather.api.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class ClienteController {

	@Autowired
	private UserRepository repU;
	
	
	@Autowired
	private ClienteService servU;
	
	
	@Autowired
	private ClienteCashingService cacheU;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	//READ
	  @Operation(description = "Esta operação possibilita obter a informação de todos os usuarios",
	            summary = "todos os usuarios", tags = "Recuperação de Informação")
	  @GetMapping()
	 public List<User> retornaTodosUser() {
	       return  repU.findAll();
	    }
	 
	 
	 
	 //READ
	  @Operation(description = "Esta operação possibilita obter a informação do usuario pelo seu id",
	            summary = "usuario por id", tags = "Recuperação de Informação")
	  @GetMapping(value = "/{id}")
	  public User retornaUserPorID(@PathVariable Long id) {
	        return repU.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario com ID: " + id + " não encontrado."));
	    }
	  
	  
	  
	  
	
	   
	  @Operation(description = "Esta operação possibilita a inserção de um novo usuario",
		        summary = "Inserir um novo usuario", tags = "Inserção de Informação")
		@PostMapping(value = "/inserir")
		@ResponseStatus(HttpStatus.CREATED)
		public User inserirUser(@RequestBody @Valid User user) {
		    
		    user.setId_usuario(null);

		 
		    if (user.getSenha() != null && !user.getSenha().isEmpty()) {
		        String senhaCriptografada = passwordEncoder.encode(user.getSenha());
		        user.setSenha(senhaCriptografada);
		    }
		    
		    
		    if (user.getEndereco() != null && user.getEndereco().getCep() != null) {
		        String cep = user.getEndereco().getCep().replaceAll("[^0-9]", "");

		        String url = "https://viacep.com.br/ws/" + cep + "/json/";
		        ViaCepDTO response = restTemplate.getForObject(url, ViaCepDTO.class);

		        if (response == null || response.getCep() == null) {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
		        }

		        Endereco endereco = new Endereco();
		        endereco.setCep(response.getCep());
		        endereco.setLogradouro(response.getLogradouro());
		        endereco.setBairro(response.getBairro());
		        endereco.setLocalidade(response.getLocalidade());
		        endereco.setUf(response.getUf());

		        endereco = enderecoRepository.save(endereco);
		        user.setEndereco(endereco);
		    } else {
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP é obrigatório para cadastro de endereço.");
		    }

		    
		    if (user.getTelefone() != null && user.getTelefone().getId_telefone() != null) {
		        Optional<Telefone> telOpt = telefoneRepository.findById(user.getTelefone().getId_telefone());
		        if (telOpt.isPresent()) {
		            user.setTelefone(telOpt.get());
		        } else {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
		                    "Telefone com ID " + user.getTelefone().getId_telefone() + " não encontrado.");
		        }
		    }

		    return repU.save(user);
		}
	    
	    
	    //UPDATE
	  @PutMapping(value = "/atualizar/{id}")
	  @Operation(description = "Esta operação vai atualizar um usuario por seu ID.",
	          summary = "Atualizar o usuario", tags = "Atualização de dados")
	  public User atualizarUserPorId(@PathVariable Long id, @Valid @RequestBody User user) {
	      Optional<User> op = repU.findById(id);

	      if (op.isPresent()) {
	          User conf_user = op.get();

	          if (user.getNome() != null && !user.getNome().isEmpty()) {
	              conf_user.setNome(user.getNome());
	          }
	          
	          if (user.getEmail() != null && !user.getEmail().isEmpty()) {
	              conf_user.setEmail(user.getEmail());
	          }

	     
	          if (user.getSenha() != null && !user.getSenha().isEmpty()) {
	              String senhaCriptografada = passwordEncoder.encode(user.getSenha());
	              conf_user.setSenha(senhaCriptografada);
	          }

	          // Atualizar telefone (se fornecido)
	          if (user.getTelefone() != null) {
	              if (user.getTelefone().getId_telefone() != null) {
	                  // Se tem ID, busca o telefone existente
	                  Optional<Telefone> telOpt = telefoneRepository.findById(user.getTelefone().getId_telefone());
	                  if (telOpt.isPresent()) {
	                      conf_user.setTelefone(telOpt.get());
	                  }
	              } else {
	                  // Se não tem ID, salva novo telefone
	                  Telefone novoTelefone = telefoneRepository.save(user.getTelefone());
	                  conf_user.setTelefone(novoTelefone);
	              }
	          }

	          // Atualizar endereço via ViaCEP (se fornecido)
	          if (user.getEndereco() != null && user.getEndereco().getCep() != null) {
	              String cep = user.getEndereco().getCep().replaceAll("[^0-9]", "");

	              String url = "https://viacep.com.br/ws/" + cep + "/json/";
	              ViaCepDTO response = restTemplate.getForObject(url, ViaCepDTO.class);

	              if (response == null || response.getCep() == null) {
	                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
	              }

	              Endereco endereco = new Endereco();
	              endereco.setCep(response.getCep());
	              endereco.setLogradouro(response.getLogradouro());
	              endereco.setBairro(response.getBairro());
	              endereco.setLocalidade(response.getLocalidade());
	              endereco.setUf(response.getUf());

	              endereco = enderecoRepository.save(endereco);
	              conf_user.setEndereco(endereco);
	          }

	          return repU.save(conf_user);
	      } else {
	          throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	                  "Usuario com ID " + id + " não encontrado para atualização.");
	      }
	  }

	    
	    
	    @Operation(description = "Esta operação retorna todas os usuarios existentes "
	      		+ "utilizando a estratégia de caching",
	      		summary = "Retornar todos os usuarios utilizando caching", tags = "Retorno de Informação")
	    	@GetMapping("/cacheable")
	    	public List<User>retornaTodosUsuariosCacheables(){
	    	List<User> todos_usuarios =  cacheU.findAll();
	    		
	    		for(User u : todos_usuarios) {
	    			u.add(linkTo(methodOn(ClienteController.class)
	    			.retornaUserPorID(u.getId_usuario()))
	    			.withRel("Quer saber mais detalhes sobre o usuario " + u.getId_usuario() + "?"));
	    			
	    			u.add(linkTo(methodOn(ClienteController.class)
	    					.retornaUsuarioPaginados(null, null))
	    					.withRel("Quer retornar usuarios paginados?"));
	    			
	    			u.add(linkTo(methodOn(ClienteController.class)
	    					.inserirUser(null)).withRel("Quer inserir um novo usuario"));
	    			
	    			u.add(linkTo(methodOn(ClienteController.class)
	    					.atualizarUserPorId(u.getId_usuario(), null))
	    					.withRel("Quer atualizar um usuario " + u.getId_usuario() +"?"));
	    		}
	    		
	    		return todos_usuarios;
	    		
	    	}
	    
	    
	    
	    
	    
	    @Operation(description = "Esta operação possibilita na busca paginada dos gerentes",
    			summary = "busca paginada", tags="Busca paginada")
    @GetMapping(value="/paginada")
    public ResponseEntity<Page<UserDTO>> retornaUsuarioPaginados(
    		@RequestParam(value= "page", defaultValue = "0")Integer page,
    		@RequestParam(value= "size", defaultValue = "7")Integer size){
    	
    	PageRequest req = PageRequest.of(page,size);
    	Page<UserDTO> usuario_paginado = servU.paginar(req);
    
    	usuario_paginado.forEach(g ->{
    		
    	});

    	return ResponseEntity.ok(usuario_paginado);
    
    
    }
	    
	    
	    
	    
	    //DELETE
	    @Operation(description = "Esta operação possibilita deletar os usuarios baseado em id ",
    			summary = "deleta usuario", tags="deleta usuario")
	    @DeleteMapping(value="/delete/{id}")
	    public User deleteUserPorId(@PathVariable Long id) {
	        Optional<User> op = repU.findById(id);
	        if (op.isPresent()) {
	            User user_remover = op.get();
	            repU.delete(user_remover);
	            return user_remover;
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com ID " + id + " não encontrado para exclusão.");
	        }
	    }
	    
	    
	    
	    
	    
}
