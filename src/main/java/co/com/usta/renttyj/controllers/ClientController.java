package co.com.usta.renttyj.controllers;

import co.com.usta.renttyj.entity.Client;
import co.com.usta.renttyj.models.services.client.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ClientController {

    final ClientService clientService;
    private Client clientObject;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        initObject();
    }

    @GetMapping("/admin-clients")
    public String getAllClients(Model model) {
        model.addAttribute("title", "Administrar Clientes");
        model.addAttribute("titlePage", "Clientes");
        model.addAttribute("clients", clientService.findAll());
        return "client/admin-clients";
    }

    @GetMapping("create-client")
    public String createClient(Model model) {
        model.addAttribute("title", "Crear Cliente");
        model.addAttribute("titlePage", "Clientes");
        model.addAttribute("clientObject", new Client());
        return "client/save-client";
    }

    @PostMapping("save-client")
    public String saveClient(@Valid Client client) {
        setParameters(client);
        clientService.save(clientObject);
        initObject();
        return "redirect:/admin-clients";
    }

    @RequestMapping("/delete-client/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/admin-clients";
    }

    @RequestMapping("/update-client/{id}")
    public String update(@PathVariable Long id, Model model) throws Exception {
        clientObject = clientService.findById(id);
        model.addAttribute("clientObject", clientObject);
        model.addAttribute("title", "Actualizar Cliente");
        model.addAttribute("titlePage", "Clientes");
        return "client/save-client";
    }

    private void setParameters(Client client) {
        clientObject.setFirstName(client.getFirstName());
        clientObject.setLastName(client.getLastName());
        clientObject.setContactNumber(client.getContactNumber());
        clientObject.setAddress(client.getAddress());
        clientObject.setLocation(client.getLocation());
    }

    private void initObject() {
        this.clientObject = new Client();
    }

}
