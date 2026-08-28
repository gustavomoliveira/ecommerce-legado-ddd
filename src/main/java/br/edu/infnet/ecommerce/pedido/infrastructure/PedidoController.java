package br.edu.infnet.ecommerce.pedido.infrastructure;

import br.edu.infnet.ecommerce.pedido.application.PedidoService;
import br.edu.infnet.ecommerce.pedido.domain.Pedido;
import br.edu.infnet.ecommerce.pedido.domain.PedidoId;
import br.edu.infnet.ecommerce.pedido.infrastructure.request.CriarPedidoRequest;
import br.edu.infnet.ecommerce.pedido.infrastructure.response.PedidoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return pedidoService.listar().stream()
                .map(PedidoResponse::de)
                .toList();
    }

    @GetMapping("/{id}")
    public PedidoResponse buscar(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscar(new PedidoId(id));
        return PedidoResponse.de(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criar(@Valid @RequestBody CriarPedidoRequest request) {
        Pedido pedido = pedidoService.criar(request);
        return PedidoResponse.de(pedido);
    }
}