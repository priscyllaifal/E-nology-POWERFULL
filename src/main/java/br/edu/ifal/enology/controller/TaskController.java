package br.edu.ifal.enology.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifal.enology.model.Palavra;
import br.edu.ifal.enology.model.Tarefa;
import br.edu.ifal.enology.model.Usuario;
import br.edu.ifal.enology.repository.ConteudoRepository;
import br.edu.ifal.enology.repository.PalavraRepository;
import br.edu.ifal.enology.repository.TarefaRepository;
import br.edu.ifal.enology.service.SequenciadorService;

@RequestMapping("/licao")
@RestController
public class TaskController {

    @Autowired
    TarefaRepository tarefaRepository;
    @Autowired
    PalavraRepository palavraRepository;
    @Autowired
    ConteudoRepository conteudoRepository;
    @Autowired
    SequenciadorService sequenciadorService;
    Tarefa tarefa;
    Usuario usuarioLogado;
    Long resposta;

    @RequestMapping("/cadastrar")
    public ModelAndView cadastrar(Palavra palavra, Tarefa tarefa) {
        if (palavra.getIngles() != null) {
            palavraRepository.save(palavra);
        } else {
            tarefaRepository.save(tarefa);
        }

        return new ModelAndView("redirect:/tarefa");
    }

    @RequestMapping("/corrigir")
    public ModelAndView corrigirResposta(Long palavra, RedirectAttributes redirect) {
        ModelAndView model = new ModelAndView("redirect:/licao/condicionais");
        resposta = palavra;
        if (tarefa.getResposta().getId().equals(palavra)) {
            usuarioLogado.setPontuacaoDoAluno(usuarioLogado.getPontuacaoDoAluno() + tarefa.getPontuacao());
            sequenciadorService.adicionarTarefaJaRespondida(tarefa);
            System.out.println(tarefa.getEnunciado());
        }        
        
        redirect.addFlashAttribute("resposta", palavra);
        return model;
    }

    @RequestMapping("/condicionais")
    public ModelAndView licao(Authentication authentication) {
        ModelAndView model = new ModelAndView("task/licao1");
        usuarioLogado = (Usuario) authentication.getPrincipal();
        // Random random = new Random();
        // List<Tarefa> tarefas = tarefaRepository.findAll();
        // int indexSorteio = random.nextInt(tarefas.size());
        try {      
          //System.out.println(resposta);
            if(resposta == null){
                tarefa = sequenciadorService.buscarTarefa();
            }
            
            resposta = null;
            List<Palavra> palavrasEncontradas = sequenciadorService.buscarPalavrasPorConteudo(
                                                "condicionais", tarefa.getResposta().getIngles());
            model.addObject("tarefa", tarefa)
                 .addObject("palavras", palavrasEncontradas)
                 .addObject("usuario", usuarioLogado);
        } catch (NullPointerException e) {
            model = new ModelAndView("redirect:/mapa");
        }
        return model;
    }
}