package ch.zhaw.iwi.devops.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/fahrraeder") // Ändere den Basispfad
public class FahrradController {

    private Map<Integer, Fahrrad> fahrraeder = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        fahrraeder.put(1, new Fahrrad(1, "Trek Emonda"));
        fahrraeder.put(2, new Fahrrad(2, "Specialized Tarmac"));
        fahrraeder.put(3, new Fahrrad(3, "Cannondale Synapse"));
        System.out.println("Init Daten für Fahrräder geladen");
    }

    @GetMapping("/{id}")
    public Fahrrad getFahrrad(@PathVariable Integer id) {
        return fahrraeder.get(id);
    }

    @PostMapping("/")
    public Fahrrad addFahrrad(@RequestBody Fahrrad fahrrad) {
        int newId = fahrraeder.keySet().stream().max(Integer::compare).orElse(0) + 1;
        fahrrad.setId(newId);
        fahrraeder.put(newId, fahrrad);
        return fahrrad;
    }

    @DeleteMapping("/{id}")
    public Fahrrad deleteFahrrad(@PathVariable Integer id) {
        return fahrraeder.remove(id);
    }

    /**
     * Aktualisiert ein bestehendes Fahrrad mit der gegebenen ID.
     * @param id Die ID des zu aktualisierenden Fahrrads.
     * @param fahrrad Die neuen Daten des Fahrrads.
     * @return Das aktualisierte Fahrrad.
     */
    @PutMapping("/{id}")
    public Fahrrad updateFahrrad(@PathVariable int id, @RequestBody Fahrrad fahrrad) {
        if (!fahrraeder.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fahrrad mit ID " + id + " nicht gefunden");
        }
        fahrrad.setId(id);  // Stelle sicher, dass die ID nicht geändert wird.
        fahrraeder.put(id, fahrrad);  // Aktualisiere das Fahrrad in der Map.
        return fahrrad;  // Gib das aktualisierte Fahrrad zurück.
    }
}
